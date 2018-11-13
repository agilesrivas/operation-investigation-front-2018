<template>
  <div class="__row __full-height-perc">
    <div id="form-container" class="__col __col-3 __align-self-center">
      <div class="__card" v-if="loading">
        <header class="__form-header">
          <h2 class="__title">Proveedor</h2>
        </header>
        <div class="__form-body">
          <div class="__loading-container __text-center">
            <i class="fas fa-spinner fa-spin"></i> Loading
          </div>
        </div>
      </div>
      <div v-else>
        <form action="" class="__card" v-if="form == 'create'">
          <header class="__form-header">
            <h2 class="__title">Proveedor</h2>
            <p class="__description">Agrega un nuevo proveedor</p>
          </header>
          <div class="__form-body">
            <div class="__form-group">
              <label for="provider_name">Nombre: </label>
              <input id="provider_name" type="text" required placeholder=" " v-model.trim="name">
            </div>
            <div class="__form-group">
              <label for="provider_email">Email: </label>
              <input id="provider_email" type="email" required placeholder=" " v-model.trim="email">
            </div>
            <div class="__form-group">
              <label for="provider_phone">Teléfono: </label>
              <input id="provider_phone" type="text" required placeholder=" " v-model.number="phone">
            </div>
             <div class="__form-group">
              <label for="provider_phone">Tiempo de Revision :T : </label>
              <input id="provider_phone" type="number" required placeholder=" " v-model.number="leadtime">
            </div>
          </div>
          <footer class="__form-footer">
            <div class="__message __message-success __mb-1" v-if="success">
              {{ success }}
            </div>
            <div class="__message __message-danger __mb-1" v-else-if="error">
              {{ error }}
            </div>
            <div class="__form-group-buttons">
              <button class="__button __button-success" @click.prevent="create">Agregar</button>
              <button class="__button __button-secondary" @click.prevent="clean">Limpiar campos</button>
            </div>
          </footer>
        </form>

        <form action="" class="__card" v-else-if="form == 'update'">
          <header class="__form-header">
            <h2 class="__title">Proveedor</h2>
            <p class="__description">Modifica un proveedor existente</p>
          </header>
          <div class="__form-body">
            <div class="__form-group">
              <label for="category_id">ID: </label>
              <input id="category_id" type="number" required placeholder=" " v-model.number="id" disabled>
            </div>
            <div class="__form-group">
              <label for="provider_name">Nombre: </label>
              <input id="provider_name" type="text" required placeholder=" " v-model.trim="name">
            </div>
            <div class="__form-group">
              <label for="provider_email">Email: </label>
              <input id="provider_email" type="email" required placeholder=" " v-model.trim="email">
            </div>
            <div class="__form-group">
              <label for="provider_phone">Teléfono: </label>
              <input id="provider_phone" type="text" required placeholder=" " v-model.number="phone">
            </div>
                  <div class="__form-group">
              <label for="provider_phone">Lead Time: </label>
              <input id="provider_phone" type="number" required placeholder=" " v-model.number="leadtime">
            </div>
          </div>
          <div class="__form-footer">
            <div class="__message __message-success __mb-1" v-if="success">
              {{ success }}
            </div>
            <div class="__message __message-danger __mb-1" v-else-if="error">
              {{ error }}
            </div>
            <div class="__form-group-buttons">
              <button class="__button __button-warning" @click.prevent="update">Modificar</button>
              <button class="__button __button-secondary" @click.prevent="clean">Limpiar campos</button>
            </div>
          </div>
          <div class="__form-footer __mt-1">
            <div class="__form-group-buttons">
              <button class="__button __button-success" @click.prevent="triggerForm" value="-1">Quiero crear</button>
            </div>
          </div>
        </form>
      </div>
    </div>
    <div class="__col __col-7">
      <div id="table-container" class="__card __max-full-height-perc">
        <table id="table" class="__table __full-width-perc __text-center __table-bordered __table-hoverrable">
          <thead>
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Email</th>
              <th>Teléfono</th>
              <th>Lead Time</th>
              <th colspan="2">Extra</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(provider, index) in providers" :key="index">
              <td> {{ provider.id }} </td>
              <td> {{ provider.name }} </td>
              <td> {{ provider.email }} </td>
              <td> {{ provider.phone }} </td>
              <td> {{ provider.leadtime }} </td>
              <td>
                <button class="__button __button-warning __button-rounded fas fa-pencil-alt" @click="triggerForm" :value="provider.id"></button>
              </td>
              <td>
                <button class="__button __button-danger __button-rounded fas fa-times" @click="remove" :value="provider.id"></button>
              </td>
            </tr>
          </tbody>
          <tfoot>
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Email</th>
              <th>Teléfono</th>
              <th>Lead Time</th>

              <th colspan="2">Extra</th>
            </tr>
          </tfoot>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import ProviderService from '@/services/ProviderService'

export default {
  name: 'ProviderComponent',
  data() {
    return {
      id: 0,
      name: '',
      email: '',
      phone: '',
      leadtime:0,
      success: '',
      error: '',
      loading: false,
      form: 'create',
      providers: []
    }
  },
  methods: {
    create() {
      if ( this.name.trim() && this.email.trim()  ) {
        const provider = {
         
          name: this.name,
          email: this.email,
          phone: this.phone,
          leadtime:this.leadtime
        }

        ProviderService.create(provider)
          .then(resolve => {
            this.success = "Carga Exitosamente"
        ProviderService.retrieveAll().then((response)=>{
              this.providers=response;
            }).catch((error)=>{
              console.log(error);
            });
            setTimeout(() => {
              this.success = undefined
            }, 2500)
            this.clean()
          })
          .catch(reject => {
            this.error = reject

            setTimeout(() => {
              this.error = undefined
            }, 2500)
          }) 
      } else {
        this.error = 'Llene todos los campos.'

        setTimeout(() => {
          this.error = undefined
        }, 2500)
      }
    },
    remove(e) { 
      const target = e.target
      target.disabled = true
      target.classList.remove('fa-times')
      target.classList.add('fa-sync')
      target.classList.add('fa-spin')

      ProviderService.remove(target.value)
        .then(resolve => {
          target.classList.remove('fa-sync')
          target.classList.remove('fa-spin')
          target.classList.add('fa-times')
          target.disabled = false
        })
          ProductService.retrieveAll().then((response)=>{
              this.providers=response;
            }).catch((error)=>{
              console.log(error);
            });
    },
    update() {
      if ( this.id > 0 && this.name.trim() && this.email.trim() && this.phone.trim() ) {
        const provider = {
          id: this.id,
          name: this.name,
          email: this.email,
          phone: this.phone,
          leadtime:this.leadtime
        }

        ProviderService.update(provider)
          .then(resolve => {
            this.success = "Modificado Exitosamente"
 ProviderService.retrieveAll().then((response)=>{
              this.providers=response;
            }).catch((error)=>{
              console.log(error);
            });
            setTimeout(() => {
              this.success = undefined
            }, 2500)
          })
          .catch(reject => {
            this.error = reject

            setTimeout(() => {
              this.error = undefined
            }, 2500)
          }) 
      } else {
        this.error = 'Llene todos los campos.'

        setTimeout(() => {
          this.error = undefined
        }, 2500)
      }
    },
    triggerForm(e) {
      const target = e.target

      if (target.value == -1) {
        this.loading = true
        this.form = 'create'
        this.clean()
        this.loading = false

      } else {
        target.disabled = true
        target.classList.remove('fa-pencil-alt')
        target.classList.add('fa-sync')
        target.classList.add('fa-spin')

        ProviderService.retrieve(target.value)
          .then(resolve => {
            this.id = resolve.id
            this.name = resolve.name
            this.email = resolve.email
            this.phone = resolve.phone
            this.leadtime=resolve.leadtime
             
            target.classList.remove('fa-sync')
            target.classList.remove('fa-spin')
            target.classList.add('fa-pencil-alt')
            target.disabled = false

            this.form = 'update'
            this.loading = false
          })
      }
    },
    clean() {
      this.name = ''
      this.email = ''
      this.phone = ''
      this.leadtime=''
    },
  },
  created() {
    ProviderService.retrieveAll()
      .then(resolve => {
        this.providers = resolve
      })
  },
  mounted() {
    const table_container = document.getElementById('table-container'),
      table = document.getElementById('table'),
      table_container_height = table_container.clientHeight,
      table_height = table.clientHeight
      
    if (table_container_height <= table_height) {
      table_container.parentElement.classList.remove('__align-self-center')
      table_container.classList.add('__overflow-scroll-y')
    } else {
      table_container.parentElement.classList.add('__align-self-center')
      table_container.classList.remove('__overflow-scroll-y')
    }
  }
}
</script>

