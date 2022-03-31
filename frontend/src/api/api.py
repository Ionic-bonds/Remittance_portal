from fastapi import FastAPI, Request, HTTPException, UploadFile, File, Form
from fastapi.middleware.cors import CORSMiddleware
from fastapi.responses import JSONResponse
from pydantic import BaseModel

import json
import uvicorn
import pathlib
import pandas as pd

app = FastAPI()
SRC_PATH = str(pathlib.Path(__file__).parent.parent.resolve())

origins = [
    "http://localhost:3000",
    "*"
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)




@app.post("/api/auth/signin")
async def get_body(request: Request):
    ########################################################################
    # SAMPLE REQUEST: 
    # {
    # "username" : "admin",
    # "password" : "password"
    # }
    ########################################################################
    response = await request.json()
    
    # Get Data
    with open(SRC_PATH + '/data/user.json', 'r') as f:
        data = json.load(f)

        dataDict = {}
        for credential in data["credentials"]:
            dataDict[credential["user"]] = credential["pw"]

    # Validation
    if response["username"] in dataDict.keys() and dataDict[response["username"]] == response["password"]:
        result = {}
        result["message"] = "Login successful!"
        result["status"] = 201
        result["username"] = response["username"]
        return result
    else:
        # raise HTTPException(status_code=401, detail="Incorrect username/password. Try again!")
        return JSONResponse(status_code=401,content={"message": "Incorrect username/password. Try again!"})

    

@app.post("/api/auth/signup")
async def get_body(request: Request):
    ########################################################################
    # SAMPLE REQUEST: 
    # {
    # "username" : "admin",
    # "password" : "password"
    # }
    ########################################################################
    response = await request.json()

    # Get data, validate and add:
    with open(SRC_PATH + '/data/user.json', 'r+') as f:
        data = json.load(f)

        if response["username"] in list(map(lambda x: x["user"],data["credentials"])):
            return JSONResponse(status_code=401,content={"message": f"Username ({response['username']}) already exist!"})
        else:
            data["credentials"].append({"user": response["username"], "pw": response["password"]})
            f.seek(0)
            json.dump(data, f, indent=4)

            return JSONResponse(status_code=201,content={"message": "Account created! Log in now!"})


@app.post("/api/upload")
# async def upload(file: bytes = File(...), headerRow: int = Form(...), username: str = Form(...)):
async def upload(file: UploadFile = File(...), headerRow: int = Form(...), username: str = Form(...)):
    ########################################################################
    # SAMPLE REQUEST: 
    # {
    # "file" : b"213nfdsgf7832", (File in btyes)
    # "headerRow" : 1
    # "username" : "admin"
    # }
    # data = file.decode('utf-8').splitlines()
    # print(data[0])
    ########################################################################
    try:
        content = await file.read()

        result = {}
        result["username"] = username
        # Read file and use headCol as the starting header
        if file.filename[-4:] != ".csv":
            df = pd.read_excel(content, sheet_name= 0, header= [headerRow - 1])
            result["headerFields"] = list(df.columns)
        

        # Get all apis fields
        with open(SRC_PATH + '/data/apiFields.json', 'r') as f:
            apiData = json.load(f)
            
            # # Create a sample userMap object (initial mapping)
            # mapDict = {}
            # mapDict["username"] = username
            # mapping = list(map(lambda x: {"apiFieldId": x["apiFieldId"]}, apiData))
            # mapping = [dict(item, map='') for item in mapping]
            # mapDict["map"] = mapping
            # with open(SRC_PATH + '/data/userMap.json', 'w') as f:
            #     json.dump(mapDict, f, indent=4)

        # Get Mappings (if available - initial mapping)
        with open(SRC_PATH + '/data/userMap.json', 'r') as f:
            mapData = json.load(f)

        # Join the two data, apiData & mapData
        editedMap = {}
        for item in mapData["map"]:
            editedMap[item["apiFieldId"]]= item["map"]

        finalData = [dict(item, map=editedMap[item["apiFieldId"]]) for item in apiData]
        result["data"] = finalData

        return JSONResponse(status_code=201,content=result)
    except Exception as e:
        return JSONResponse(status_code=401,content={"message": "Import Error! "+ repr(e)})

@app.post("/api/getAPI")
async def upload(file: UploadFile = File(...), column: str = Form(...)):
    try:
        content = await file.read()

        result = {}
        # # Read file and use headCol as the starting header
        # if file.filename[-4:] != ".csv":
        #     df = pd.read_excel(content, sheet_name= 0, header= [headerRow - 1])
        #     result["headerFields"] = list(df.columns)
        

        # # Get all apis fields
        # with open(SRC_PATH + '/data/apiFields.json', 'r') as f:
        #     apiData = json.load(f)
            
        #     # # Create a sample userMap object (initial mapping)
        #     # mapDict = {}
        #     # mapDict["username"] = username
        #     # mapping = list(map(lambda x: {"apiFieldId": x["apiFieldId"]}, apiData))
        #     # mapping = [dict(item, map='') for item in mapping]
        #     # mapDict["map"] = mapping
        #     # with open(SRC_PATH + '/data/userMap.json', 'w') as f:
        #     #     json.dump(mapDict, f, indent=4)

        # # Get Mappings (if available - initial mapping)
        # with open(SRC_PATH + '/data/userMap.json', 'r') as f:
        #     mapData = json.load(f)

        # # Join the two data, apiData & mapData
        # editedMap = {}
        # for item in mapData["map"]:
        #     editedMap[item["apiFieldId"]]= item["map"]

        # finalData = [dict(item, map=editedMap[item["apiFieldId"]]) for item in apiData]
        # result["data"] = finalData

        return JSONResponse(status_code=201,content=result)
    except Exception as e:
        return JSONResponse(status_code=401,content={"message": "Retrieving Error! "+ repr(e)})


if __name__ == "__main__":
    uvicorn.run("api:app", host="0.0.0.0", port=8080, reload=True)