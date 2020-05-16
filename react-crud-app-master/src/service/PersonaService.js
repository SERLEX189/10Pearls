import axios from 'axios';

export class PersonaService {
    baseUrl = "http://localhost:8080/api";
    baseReportUrl = "http://localhost:8080/api/visit";
    
    getAll(){
        return axios.get(this.baseUrl + "/clients").then(res => res.data);
    }


    getAllSalesRepresent(){
        return axios.get(this.baseReportUrl + "/SalesRepresent").then(res => res.data);
    }

    getAllCities(){
        return axios.get(this.baseUrl + "/cities").then(res => res.data);
    }
    getAllStates(){
        return axios.get(this.baseUrl + "/states").then(res => res.data);
    }
    getAllCountries(){
        return axios.get(this.baseUrl + "/countries").then(res => res.data);
    }

    getReport(){
        return axios.get(this.baseReportUrl + "/report").then(res => res.data);
    }

    save(client) {
        return axios.post(this.baseUrl + "/client", client).then(res => res.data);
    }

    delete(nit) {
        return axios.delete(this.baseUrl + "/client/"+nit).then(res => res.data);
    }

    saveVisit(visit) {
        console.log(visit);
        return axios.post(this.baseUrl + "/visit", visit).then(res => res.data);
    }
}