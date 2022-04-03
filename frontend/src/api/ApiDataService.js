import http from "../api/http-common";
import httpImport from "../api/http-import";

class ApiDataService {
  // ##################### Use API ##################### //
  // API 1
  login(data){
    return http.post("/auth/signin", data);
  }
  // API 2
  signup(data){
    return http.post("/auth/signup", data);
  }
  // API 20
  getMappingsAndFields(id){
    return http.get(`/getAllCorpFieldByCorpUserId/${id}`);
  }
  // API 29
  getFileHeaders(data, id, headerRow){
    return httpImport.post(`/uploadExcelHeader/${id}/${headerRow}`, data);
  }

  // API 24
  addMapping(data){
    return http.post("/addFieldMapping", data);
  }

  // API 27
  validateMap(file, id){
    return httpImport.post(`/uploadFieldMapping/${id}`, file);
  }

  // API 36
  getTransactionReq(id){
    return http.get(`/getAllUserApiRequestByCorpId/${id}`);
  }
  

}
export default new ApiDataService();
