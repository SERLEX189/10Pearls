import React, { Component } from 'react';
import './App.css';
import { PersonaService } from './service/PersonaService';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import {Panel} from 'primereact/panel';
import {Menubar} from 'primereact/menubar';
import {Dialog} from 'primereact/dialog';
import {InputText} from 'primereact/inputtext';
import {Button} from 'primereact/button';
import {Dropdown} from 'primereact/dropdown';
import {Growl} from 'primereact/growl';


import 'primereact/resources/themes/nova-light/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';

export default class App extends Component{
  constructor(){
    super();
    this.state = {
      visibleReportCLient : false,
      visibleReport : false,
      visibleVisit : false,
      visible : false,
      client : {
        nit : null,
        fullName : null,
        address : null,
        phone : null,
        creditLimit : null,
        availableCredit : null,
        visitsPercentage : null,
        city_id : null,
        clientIdState : null,
        clientIdCountry : null,
        visitClients : null
      },
      visit : {
        idVisit : null,
        cityId : null,
        idSale : null,
        createAt : null,
        net : null,
        visitTotal : null,
        description : null,
        clientNit : null
      },
      VisitReport : {
        cityId : null,
        cityName : null,
        visitNumber : null
      },
      selectedPersona : {

      }
    };
    this.items = [
      {
        label : 'New CLient',
        icon  : 'pi pi-fw pi-plus',
        command : () => {this.showSaveDialog()}
      },
      {
        label : 'Edit Client',
        icon  : 'pi pi-fw pi-pencil',
        command : () => {this.showEditDialog()}
      },
      {
        label : 'Delete Client',
        icon  : 'pi pi-fw pi-trash',
        command : () => {this.delete()}
      },
      {
        label : 'Register Visit',
        icon  : 'pi pi-fw pi-plus', 
        command : () => {this.showRegisterDialog()}
      },
      {
        label : 'Visit by City',
        icon  : 'pi pi-fw pi-file-o',
        command : () => {this.showVisitbyCityDialog()}
      },
      {
        label : 'Credit limit for a client',
        icon  : 'pi pi-fw pi-file-o',
        command : () => {this.showCreditLimitDialog()}
      }


      
    ];
    
    this.personaService = new PersonaService();
    this.save = this.save.bind(this);
    this.delete = this.delete.bind(this);
    this.registerVisit = this.registerVisit.bind(this);

    this.footer = (
      <div>
        <Button label="Guardar" icon="pi pi-check" onClick={this.save} />
      </div>
    );
    this.footerVisit = (
      <div>
        <Button label="Guardar" icon="pi pi-check" onClick={this.registerVisit} />
      </div>
    );
    
  }

  componentDidMount(){
    this.personaService.getAll().then(data => this.setState({clientes: data}));
    this.personaService.getAllCities().then(data => this.setState({cities: data}));
    this.personaService.getAllStates().then(data => this.setState({states: data}));
    this.personaService.getAllCountries().then(data => this.setState({countries: data}));
    this.personaService.getAllSalesRepresent().then(data => this.setState({salesRepresent: data}));
    this.personaService.getReport().then(data => this.setState({reporte: data}));
  }

  save() {
    this.personaService.save(this.state.client).then(data => {
      this.setState({
        visible : false,
        client : {
          nit : null,
          fullName : null,
          address : null,
          phone : null,
          creditLimit : null,
          availableCredit : null,
          visitsPercentage : null,
          city_id : null,
          clientIdState : null,
          clientIdCountry : null,
          visitClients : null
        }
      });
      this.growl.show({severity: 'success', summary: 'Atención!', detail: 'Se guardó el registro correctamente.'});
      this.personaService.getAll().then(data => this.setState({clientes: data}))
    })
  }

  registerVisit() {
    this.personaService.saveVisit(this.state.visit).then(data => {
      this.setState({
        visibleVisit : false,
        visit : {
          idVisit : null,
          cityId : null,
          idSale : null,
          createAt : null,
          net : null,
          visitTotal : null,
          description : null,
          clientNit : null
        }
      });
      this.growl.show({severity: 'success', summary: 'Atención!', detail: 'Se registro la visita'});
      this.personaService.getAll().then(data => this.setState({clientes: data}));
      this.personaService.getReport().then(data => this.setState({reporte: data}));
    })
  }
  

  delete() {
    if(window.confirm("¿Realmente desea eliminar el registro?")) {
      this.personaService.delete(this.state.selectedPersona.nit).then(data => {
        this.growl.show({severity: 'success', summary: 'Atención!', detail: 'Se eliminó el registro correctamente.'});
        this.personaService.getAll().then(data => this.setState({clientes: data}));
        this.personaService.getReport().then(data => this.setState({reporte: data}));
      });
    }
  }

  render(){
    return (
      <div style={{width:'80%', margin: '0 auto', marginTop: '20px'}}>
        <Menubar model={this.items}/>
        <br/>
        <Panel header="React CRUD App">
            <DataTable value={this.state.clientes} paginator={true} rows="4" selectionMode="single" selection={this.state.selectedPersona} onSelectionChange={e => this.setState({selectedPersona: e.value})}>
              <Column field="nit" header="NIT"></Column>
              <Column field="fullName" header="Full Name"></Column>
              <Column field="address" header="Address"></Column>
              <Column field="creditLimit" header="Credit Limit"></Column>
              <Column field="availableCredit" header="Available Credit"></Column>
              <Column field="visitsPercentage" header="Visits Percentage"></Column>
            </DataTable>
        </Panel>
        <Dialog header="Crear persona" visible={this.state.visible} style={{width: '400px'}} footer={this.footer} modal={true} onHide={() => this.setState({visible: false})}>
            <form id="persona-form">
              <span className="p-float-label">
                <InputText value={this.state.client.nit} style={{width : '100%'}} id="nit" onChange={(e) => {
                    let val = e.target.value;
                    this.setState(prevState => {
                        let client = Object.assign({}, prevState.client);
                        client.nit = val;

                        return { client };
                    })}
                  } />
                <label htmlFor="nit">nit</label>
              </span>
              <br/>
              <span className="p-float-label">
                <InputText value={this.state.client.fullName} style={{width : '100%'}} id="fullName" onChange={(e) => {
                    let val = e.target.value;
                    this.setState(prevState => {
                        let client = Object.assign({}, prevState.client);
                        client.fullName = val;

                        return { client };
                    })}
                  } />
                <label htmlFor="fullName">Full Name</label>
              </span>
              <br/>
              <span className="p-float-label">
                <InputText value={this.state.client.address} style={{width : '100%'}} id="address" onChange={(e) => {
                    let val = e.target.value;
                    this.setState(prevState => {
                        let client = Object.assign({}, prevState.client);
                        client.address = val;

                        return { client };
                    })}
                  } />
                <label htmlFor="address">Address</label>
              </span>
              <br/>
              <span className="p-float-label">
                <InputText value={this.state.client.phone} style={{width : '100%'}} id="phone" onChange={(e) => {
                    let val = e.target.value;
                    this.setState(prevState => {
                        let client = Object.assign({}, prevState.client);
                        client.phone = val;

                        return { client };
                    })}
                  } />
                <label htmlFor="phone">Phone</label>
              </span>
              <br/>
              <span className="p-float-label">
                <InputText value={this.state.client.creditLimit} style={{width : '100%'}} id="creditLimit" onChange={(e) => {
                    let val = e.target.value;
                    this.setState(prevState => {
                        let client = Object.assign({}, prevState.client);
                        client.creditLimit = val;

                        return { client };
                    })}
                  } />
                <label htmlFor="creditLimit">Credit Limit</label>
              </span>
              <br/>
              <span className="p-float-label">
                <InputText value={this.state.client.visitsPercentage} style={{width : '100%'}} id="visitsPercentage" onChange={(e) => {
                    let val = e.target.value;
                    this.setState(prevState => {
                        let client = Object.assign({}, prevState.client);
                        client.visitsPercentage = val;

                        return { client };
                    })}
                  } />
                <label htmlFor="visitsPercentage">visits Percentage</label>
              </span>
              <br/>
              <span className="p-float-label">
              <Dropdown value={this.state.client.clientIdCountry}
               options={this.state.countries} onChange={(e) => {
                let val = e.target.value;
                this.setState(prevState => {
                    let client = Object.assign({}, prevState.client);
                    client.clientIdCountry = val;

                    return { client };
                })}}
                placeholder="Select a State"/>
              </span>
              <br/>
              <span className="p-float-label">
              <Dropdown value={this.state.client.clientIdState}
               options={this.state.states} onChange={(e) => {
                let val = e.target.value;
                this.setState(prevState => {
                    let client = Object.assign({}, prevState.client);
                    client.clientIdState = val;

                    return { client };
                })}}
                placeholder="Select a State"/>
              </span>

              <br/>
              <span className="p-float-label">
              <Dropdown value={this.state.client.city_id}
               options={this.state.cities} onChange={(e) => {
                let val = e.target.value;
                this.setState(prevState => {
                    let client = Object.assign({}, prevState.client);
                    client.city_id = val;

                    return { client };
                })}}
                placeholder="Select a City"/>
              </span>

              <br/>
            </form>
        </Dialog>


        <Dialog header="Register Visit" visible={this.state.visibleVisit} style={{width: '400px'}} footer={this.footerVisit} modal={true} onHide={() => this.setState({visibleVisit: false})}>
            <form id="visit-form">

              <span className="p-float-label">
              <Dropdown value={this.state.visit.idSale}
               options={this.state.salesRepresent} onChange={(e) => {
                let val = e.target.value;
                this.setState(prevState => {
                  let visit = Object.assign({}, prevState.visit);
                  visit.idSale = val;
                    return { visit };
                })}}
                placeholder="Select a Sale Represent"/>
              </span>  
              <br/>
              <span className="p-float-label">
                <InputText value={this.state.visit.net} style={{width : '100%'}} id="net" onChange={(e) => {
                    let val = e.target.value;
                    this.setState(prevState => {
                        let visit = Object.assign({}, prevState.visit);
                        visit.net = val;

                        return { visit };
                    })}
                  } />
                <label htmlFor="net">Net</label>
              </span>  
              <br/>
              <span className="p-float-label">
                <InputText value={this.state.visit.description} style={{width : '100%'}} id="description" onChange={(e) => {
                    let val = e.target.value;
                    this.setState(prevState => {
                        let visit = Object.assign({}, prevState.visit);
                        visit.description = val;
                        return { visit };
                    })}
                  } />
                <label htmlFor="description">Description</label>
              </span> 
              <br/>
              <span className="p-float-label">
                <InputText value={this.state.visit.clientNit} style={{width : '100%'}} id="clientNit" onChange={(e) => {
                    let val = e.target.value;
                    this.setState(prevState => {
                        let visit = Object.assign({}, prevState.visit);
                        visit.clientNit = val;
                        return { visit };
                    })}
                  } />
                <label htmlFor="clientNit">Client Nit</label>
              </span> 
            </form>
        </Dialog>

        <Dialog header="Visit Report" visible={this.state.visibleReport} style={{width: '400px'}} modal={true} onHide={() => this.setState({visibleReport: false})}>
          <DataTable value={this.state.reporte} paginator={true} rows="4">
                <Column field="cityId" header="City Code"></Column>
                <Column field="cityName" header="City Name"></Column>
                <Column field="visitNumber" header="Number of visits"></Column>
          </DataTable>
        </Dialog>


        <Dialog header="Detail limit for a client" visible={this.state.visibleReportCLient} style={{width: '800px'}}  modal={true} onHide={() => this.setState({visibleReportCLient: false})}>
              <br/>
              <span className="p-float-label">
                <InputText value={this.state.client.fullName} style={{width : '30%'}} id="fullName" onChange={(e) => {
                    let val = e.target.value;
                    this.setState(prevState => {
                        let client = Object.assign({}, prevState.client);
                        client.fullName = val;
                        return { client };
                    })}
                  } />
                <label htmlFor="fullName">Full Name</label>
              </span>
              <br/>
              <span className="p-float-label">
                <InputText value={this.state.client.creditLimit} style={{width : '30%'}} id="creditLimit" onChange={(e) => {
                    let val = e.target.value;
                    this.setState(prevState => {
                        let client = Object.assign({}, prevState.client);
                        client.creditLimit = val;
                        return { client };
                    })}
                  } />
                <label htmlFor="creditLimit">Credit Limit</label>
              </span>
              <br/>
              <span className="p-float-label">
                <InputText value={this.state.client.availableCredit} style={{width : '30%'}} id="availableCredit" onChange={(e) => {
                    let val = e.target.value;
                    this.setState(prevState => {
                        let client = Object.assign({}, prevState.client);
                        client.availableCredit = val;
                        return { client };
                    })}
                  } />
                <label htmlFor="availableCredit">Available Credit</label>
              </span>
              <br/>
              <span className="p-float-label">
                <InputText value={this.state.client.visitsPercentage} style={{width : '30%'}} id="visitsPercentage" onChange={(e) => {
                    let val = e.target.value;
                    this.setState(prevState => {
                        let client = Object.assign({}, prevState.client);
                        client.visitsPercentage = val;
                        return { client };
                    })}
                  } />
                <label htmlFor="visitsPercentage">Visits Percentage</label>
              </span>
              <br/>
              <DataTable value={this.state.client.visitClients} paginator={true} rows="4" selectionMode="single" selection={this.state.selectedPersona}>
                  <Column field="idSale" header="Id Sale"></Column>
                  <Column field="createAt" header="Date"></Column>
                  <Column field="net" header="Net"></Column>
                  <Column field="description" header="Description"></Column>
                  <Column field="visitTotal" header="Total"></Column>
                  <Column field="clientNit" header="Client Nit"></Column>
                </DataTable>
        </Dialog>
        <Growl ref={(el) => this.growl = el} />
      </div>
    );
  }

  showSaveDialog(){
    this.setState({
      visible : true,
      client : {
        nit : null,
        fullName : null,
        address : null,
        phone : null,
        creditLimit : null,
        availableCredit : null,
        visitsPercentage : null,
        city_id : null,
        clientIdState : null,
        clientIdCountry : null,
        visitClients : null
      }
    });
    document.getElementById('persona-form').reset();
  }

  showRegisterDialog(){
    this.setState({
      visibleVisit : true,
      visit : {
        idVisit : null,
        cityId : null,
        idSale : null,
        createAt : null,
        net : null,
        visitTotal : null,
        description : null,
        clientNit : null
      }
    });
    document.getElementById('visit-form').reset();
  }

  showVisitbyCityDialog(){
    this.setState({
      visibleReport : true,
    });
  }

  showEditDialog() {
    this.setState({
      visible : true,
      client : {
        nit : this.state.selectedPersona.nit,
        fullName : this.state.selectedPersona.fullName,
        address : this.state.selectedPersona.address,
        phone : this.state.selectedPersona.phone,
        creditLimit : this.state.selectedPersona.creditLimit,
        visitsPercentage : this.state.selectedPersona.visitsPercentage,
        city_id : this.state.selectedPersona.city_id,
        clientIdState : this.state.selectedPersona.clientIdState,
        clientIdCountry : this.state.selectedPersona.clientIdCountry,
        visitClients : this.state.selectedPersona.visitClients
      }
    })
  }

  showCreditLimitDialog(){
    this.setState({
      visibleReportCLient : true,
      client : {
        nit : this.state.selectedPersona.nit,
        fullName : this.state.selectedPersona.fullName,
        address : this.state.selectedPersona.address,
        phone : this.state.selectedPersona.phone,
        creditLimit : this.state.selectedPersona.creditLimit,
        availableCredit : this.state.selectedPersona.availableCredit,
        visitsPercentage : this.state.selectedPersona.visitsPercentage,
        city_id : this.state.selectedPersona.city_id,
        clientIdState : this.state.selectedPersona.clientIdState,
        clientIdCountry : this.state.selectedPersona.clientIdCountry,
        visitClients : this.state.selectedPersona.visitClients
      }
    });
  }
}
