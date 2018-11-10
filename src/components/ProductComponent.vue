<template>
  <div class="__row __full-height-perc">
    <div id="form-container" class="__col __col-3 __align-self-center">
      <div class="__card" v-if="loading">
        <header class="__form-header">
          <h2 class="__title">Producto</h2>
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
            <h2 class="__title">Producto</h2>
            <p class="__description">Agrega un nuevo producto</p>
          </header>
          <div class="__form-body">
            <div class="__form-group">
              <label for="product_name">Nombre: </label>
              <input id="product_name" type="text" required placeholder=" " v-model.trim="product.name">
            </div>
            <div class="__form-group">
              <label for="product_amount">Precio: </label>
              <input id="product_amount" type="number" required placeholder=" " v-model.number="product.amount">
            </div>
            <div class="__form-group">
              <label for="product_cost">Costo: </label>
              <input id="product_cost" type="number" required placeholder=" " v-model.number="product.cost">
            </div>
            
            <div class="__form-group">
              <label for="product_code">Código: </label>
              <input id="product_code" type="text" required placeholder=" " v-model.trim="product.code">
            </div>
             <div class="__form-group">
              <label for="stock_actual">Cantidad Actual: </label>
              <input id="stock_actual" type="number" required placeholder=" " v-model.number="product.currentAmount">
            </div>
            <div class="__form-group">
              <label for="product_category">Categoría: </label>
              <select id="product_category" required v-model="product.category">
                <option value="" selected disabled>Elige una</option>
                <option :value="categoryUnit" v-for="(categoryUnit, index) in categories" :key="index">{{ categoryUnit.name }}</option>
              </select>
            </div>
              <div class="__form-group">
                <input type="checkbox" name="model" id="model-p"  v-model="check">
                <label for="model-p"> Viene mi proveedor</label>
              </div>
            <div class="__form-group" v-if="check">
              <label for="product_provider">Proveedor: </label>
              <select id="product_provider" required v-model="product.provideer">
                <option value="" selected disabled>Elige una</option>
                <option :value="providerUnit" v-for="(providerUnit, index) in providers" :key="index">{{ providerUnit.name }}</option>
              </select>
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
            <h2 class="__title">Producto</h2>
            <p class="__description">Modifica un producto existente</p>
          </header>
          <div class="__form-body __my-0">
            <div class="__form-group">
              <label for="category_id">ID: </label>
              <input id="category_id" type="number" required placeholder=" " v-model.number="product.id" disabled>
            </div>
            <div class="__form-group">
              <label for="product_name">Nombre: </label>
              <input id="product_name" type="text" required placeholder=" " v-model.trim="product.name">
            </div>
            <div class="__form-group">
              <label for="product_amount">Precio: </label>
              <input id="product_amount" type="number" required placeholder=" " v-model.number="product.amount">
            </div>
            <div class="__form-group">
              <label for="product_cost">Costo: </label>
              <input id="product_cost" type="number" required placeholder=" " v-model.number="product.cost">
            </div>
          
            <div class="__form-group">
              <label for="product_code">Código: </label>
              <input id="product_code" type="text" required placeholder=" " v-model.trim="product.code">
            </div>
           <div class="__form-group">
              <label for="stock_actual">Cantidad Actual: </label>
              <input id="stock_actual" type="number" required placeholder=" " v-model.number="product.currentAmount">
            </div>
            <div class="__form-group">
              <label for="product_category">Categoría: </label>
              <select id="product_category" required v-model="product.category">
                <option value="" selected disabled>Elige una</option>
                <option :value="categoryUnit" v-for="(categoryUnit, index) in categories" :key="index">{{ categoryUnit.name }}</option>
              </select>
            </div>
            <div class="__form-group">
              <label for="product_provider">Proveedor: </label>
              <select id="product_provider" required v-model="product.provideer">
                <option value="" selected disabled>Elige una</option>
                <option :value="providerUnit" v-for="(providerUnit, index) in providers" :key="index">{{ providerUnit.name }}</option>
              </select>
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
              <th>Nombre</th>
              <th>Precio</th>
              <th>Costo</th>
              <th>Código</th>
               <th>Cantidad actual</th>
              <th>Punto de Reorden</th>
              <th>Tiempo de revision</th>
              <th>Lead Time</th>
              <th>Categoría</th>
              <th >Proveedor</th>
              <th colspan="2">Extra</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(tock, index) in stocks" :key="index">
              <td> {{ tock.name }} </td>
              <td> {{ tock.amount }} </td>
              <td> {{ tock.cost }} </td>
              <td> {{ tock.code }} </td>
              <td> {{ tock.currentAmount}} </td>
              <td> {{ tock.reorder_point}} </td>
              <td v-if="tock.provideer!==null"> {{ tock.provideer.leadtime }} </td>
              <td> {{ tock.category.name }} </td>
              <td v-if="tock.provideer!==null"> {{ tock.provideer.name }} </td>
              <td>
                <button class="__button __button-warning __button-rounded fas fa-pencil-alt" @click="triggerForm" :value="tock.id"></button>
              </td>
              <td>
                <button class="__button __button-danger __button-rounded fas fa-times" @click="remove" :value="tock.id"></button>
              </td>
            </tr>
          </tbody>
          <tfoot>
            <tr>
              <th>Nombre</th>
              <th>Precio</th>
              <th>Costo</th>
              <th>Código</th>
              <th>Cantidad actual</th>
              <th>Punto de Reorden</th>
             <th>Tiempo de revision</th>
              <th>Lead Time</th>
              <th>Categoría</th>
              <th>Proveedor</th>
              <th colspan="2">Extra</th>
            </tr>
          </tfoot>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import CategoryService from '@/services/CategoryService'
import ProviderService from '@/services/ProviderService'
import ProductService from '@/services/ProductService'
export default {
  name: 'ProductComponent',
  data() {
    return {
        product: {
        id: 0,
        name: '',
        amount: 0,
        cost: 0,
        code: '',
        category: '',
        provideer: '',
        currentAmount:0,
        reorder_point:0,
                },
      check:false,
      success: '',
      error: '',
      loading: false,
      form: 'create',
      providers: [],
      categories: [],
      stocks: []
    }
  },
  methods: {
    create() {
      if ( this.product.name.trim() && this.product.amount >= 0 && this.product.cost >= 0  && this.product.code.trim() &&  this.product.category ) {
     let product=null;
      if(this.check){
          product={
                  name: this.product.name,
                  amount: this.product.amount,
                  cost: this.product.cost,
                  code: this.product.code,
                  category: this.product.category,
                  provideer: this.product.provideer,
                  currentAmount:this.product.currentAmount,
                  reorder_point:this.product.reorder_point
            }
            console.log(product);
      }else{
        
          product={
                  name: this.product.name,
                  amount: this.product.amount,
                  cost: this.product.cost,
                  code: this.product.code,
                   provideer:null,
                  category: this.product.category,
                  currentAmount:this.product.currentAmount,
                  reorder_point:this.product.reorder_point
            }
      }
            
            ProductService.create(product)
            .then(resolve => {            
               this.success="Cargado Exitosamente";
            ProductService.retrieveAll()
            .then((response)=>{
              this.stocks=response;
              this.clean();
            })
            .catch((error)=>{
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

      ProductService.remove(target.value)
        .then(resolve => {
          target.classList.remove('fa-sync')
          target.classList.remove('fa-spin')
          target.classList.add('fa-times')
          target.disabled = false 
          ProductService.retrieveAll().then((response)=>{
              this.stocks=response;
            }).catch((error)=>{
              console.log(error);
            });
        }).catch((error)=>{
          console.log(error);
        });
    },
    update() {
      if ( this.product.id > 0 && this.product.amount >= 0 && this.product.cost >= 0  && this.product.code.trim() &&  this.product.category && this.product.provideer ) {
         
        let product = {
                  id: this.product.id,
                  name: this.product.name,
                  amount: this.product.amount,
                  cost: this.product.cost,
                  code: this.product.code,
                  category: this.product.category,
                  provideer: this.product.provideer,
              currentAmount:this.product.currentAmount,
              reorder_point:this.product.reorder_point
            } 
            ProductService.update(product)
              .then(resolve => {
                this.success = "Modificado Exitosamente"
            ProductService.retrieveAll().then((resolve)=>{
              this.stocks=resolve;
              this.clean();
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

        ProductService.retrieve(target.value)
          .then(resolve => {
            console.log(resolve);
            this.product=resolve;
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
      this.product.id=0
      this.product.name = ''
      this.product.amount = 0
      this.product.cost = 0
      this.product.code = ''
      this.product.currentAmount=0
      this.product.category = ''
      this.product.provideer = ''
    },
  },
  created() {
    CategoryService.retrieveAll()
      .then(resolve => {
        this.categories = resolve
      })
    
    ProviderService.retrieveAll()
      .then(resolve => {
        this.providers = resolve
      })

    ProductService.retrieveAll()
      .then(resolve => {
        console.log(resolve);
        this.stocks = resolve
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