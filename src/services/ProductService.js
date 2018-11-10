import axios from 'axios';
export default {
  retrieveAll() {
    return axios.get("http://localhost:25101/product")
      .then((response)=>{
       return  response.data;
      }).catch((error)=>{
        console.log(error);
      });
      
  },
  retrieve(id) {
    console.log(id);
      return axios.get("http://localhost:25101/product/",{
        params: { id: id }})
      .then((response)=>{
        console.log(response)
        return response.data;
      }).catch((error)=>{
        console.log(error);
      });
  },
  create(product) {
      return axios.post("http://localhost:25101/product/create",product)
      .then((response)=>{
       return response.data;
      }).catch((error)=>{
        console.log(error);
      });
  },
  remove(id) {
    console.log(id);
     return  axios.delete("http://localhost:25101/product/",{
      params: { id: id }
    })
      .then((response)=>{
        return response.data;
      }).catch((error)=>{
        console.log(error);
      });
  },
  update(product) {
     return  axios.put("http://localhost:25101/product/edit",product)
      .then((response)=>{
        return response.data
      }).catch((error)=>{
        console.log(error);
      });
  }
}