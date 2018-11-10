import axios from 'axios';
export default {
  retrieveAll() {
    return axios.get("http://localhost:25101/product/all/q")
      .then((response)=>{
       return  response.data;
      }).catch((error)=>{
        console.log(error);
      });
      
  },
  All() {
    return axios.get("http://localhost:25101/product/all/p")
      .then((response)=>{
       return  response.data;
      }).catch((error)=>{
        console.log(error);
      });
      
  }
}