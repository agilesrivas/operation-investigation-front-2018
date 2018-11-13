import axios from 'axios';

export default {
    retrieveAll() {
      return axios.get("http://localhost:25101/category")
        .then((response)=>{
         return  response.data;
        }).catch((error)=>{
          console.log(error);
        });
        
    },
    retrieve(id) {
      console.log(id);
        return axios.get("http://localhost:25101/category/",{
          params: { id: id }})
        .then((response)=>{
          return response.data;
        }).catch((error)=>{
          console.log(error);
        });
    },
    create(category) {
        return axios.post("http://localhost:25101/category/create",category)
        .then((response)=>{
         return response.data;
        }).catch((error)=>{
          console.log(error);
        });
    },
    remove(id) {
      console.log(id);
       return  axios.delete("http://localhost:25101/category/",{
        params: { id: id }
      })
        .then((response)=>{
          return response.data;
        }).catch((error)=>{
          console.log(error);
        });
    },
    update(category) {
       return  axios.put("http://localhost:25101/config/edit",category)
        .then((response)=>{
          return response.data
        }).catch((error)=>{
          console.log(error);
        });
    }
  }
