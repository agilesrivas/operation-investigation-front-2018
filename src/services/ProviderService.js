import axios from 'axios';
export default {
  retrieveAll() {
    return axios.get("http://localhost:25101/provideer")
      .then((response)=>{
       return  response.data;
      }).catch((error)=>{
        console.log(error);
      });
      
  },
  retrieve(id) {
    console.log(id);
      return axios.get("http://localhost:25101/provideer/",{
        params: { id: id }})
      .then((response)=>{
        return response.data;
      }).catch((error)=>{
        console.log(error);
      });
  },
  create(provideer) {
      return axios.post("http://localhost:25101/provideer/create",provideer)
      .then((response)=>{
       return response.data;
      }).catch((error)=>{
        console.log(error);
      });
  },
  remove(id) {
    console.log(id);
     return  axios.delete("http://localhost:25101/provideer/",{
      params: { id: id }
    })
      .then((response)=>{
        return response.data;
      }).catch((error)=>{
        console.log(error);
      });
  },
  update(provideer) {
     return  axios.put("http://localhost:25101/provideer/edit",provideer)
      .then((response)=>{
        return response.data
      }).catch((error)=>{
        console.log(error);
      });
  }
}