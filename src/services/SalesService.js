import axios from 'axios';
export default {
  retrieveAll() {
    return axios.get("http://localhost:25101/sale")
      .then((response)=>{
       return  response.data;
      }).catch((error)=>{
        console.log(error);
      });
      
  },
  retrieve(id) {
    console.log(id);
      return axios.get("http://localhost:25101/sale/",{
        params: { id: id }})
      .then((response)=>{
        return response.data;
      }).catch((error)=>{
        console.log(error);
      });
  },
  create(provideer) {
      return axios.post("http://localhost:25101/sale/create",provideer)
      .then((response)=>{
       return response.data;
      }).catch((error)=>{
        console.log(error);
      });
  },

}