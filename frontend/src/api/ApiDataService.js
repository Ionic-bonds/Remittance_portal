import http from "../api/http-common";
import httpImport from "../api/http-import";

class ApiDataService {
  // getAll() {
  //   return http.get("/v1/student");
  //   // return http.get("/v1/student");
  // }
  // get(id) {
  //   // return http.get(`/tutorials/${id}`);
  //   return http.get(`/getAllCorpUsers`);
  //   return http.get(`/getCorpUserById/${id}`);
  // }
  // create(data) {
  //   return http.post("/addApi", data);
  //   return http.post("/tutorials", data);
  // }
  // update(id, data) {
  //   return http.put(`/tutorials/${id}`, data);
  // }
  // delete(id) {
  //   return http.delete(`/tutorials/${id}`);
  // }
  // deleteAll() {
  //   return http.delete(`/tutorials`);
  // }
  // findByTitle(title) {
  //   return http.get(`/tutorials?title=${title}`);
  // }

  // // currently using this
  // // user, pw, 
  // loginAuthentication(user, pw){


  //   if (user == "admin" && pw == "admin"){
  //     return true
  //   }
  //   return false
  // }
  // upload(data){
  //   return httpImport.post("/upload", data);
  // }
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

  // test(){
  //   return http.get(`/getAllCorpField`);
  // }

  

}
export default new ApiDataService();
