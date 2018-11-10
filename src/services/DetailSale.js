import axios from 'axios';
export default {
  retrieveAll() {
    return axios.get("http://localhost:25101/detailsale")
      .then((response)=>{
       return  response.data;
      }).catch((error)=>{
        console.log(error);
      });
      
  },
  retrieve(id) {
    console.log(id);
      return axios.get("http://localhost:25101/detailsale/",{
        params: { id: id }})
      .then((response)=>{
        console.log(response)
        return response.data;
      }).catch((error)=>{
        console.log(error);
      });
  },
  create(stock) {
    console.log(stock);
      return axios.post("http://localhost:25101/stock/detailsale",stock)
      .then((response)=>{
       return response.data;
      }).catch((error)=>{
        console.log(error);
      });
  },
  remove(id) {
    console.log(id);
     return  axios.delete("http://localhost:25101/detailsale/",{
      params: { id: id }
    })
      .then((response)=>{
        return response.data;
      }).catch((error)=>{
        console.log(error);
      });
  },
  update(stock) {
     return  axios.put("http://localhost:25101/stock/detailsale",stock)
      .then((response)=>{
        return response.data
      }).catch((error)=>{
        console.log(error);
      });
  }
}