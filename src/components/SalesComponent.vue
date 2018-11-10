<template>
  <div class="__row __full-height-perc">
    <div id="form-container" class="__col __col-offset-3 __col-4 __align-self-center">
      <form action="" class="__card">
        <header class="__form-header">
          <h2 class="__title">Venta</h2>
          <p class="__description">Elige un producto junto con su cantidad para venderlo.</p>
        </header>
        <div class="__form-body">
          <div class="__form-group">
            <label for="stock_product">Producto: </label>
            <select id="stock_product" required v-model="product">
              <option value="" selected disabled>Elige una</option>
              <option :value="productUnit" v-for="(productUnit, index) in products" :key="index">{{ productUnit.name }}</option>
            </select>
          </div>
          <div class="__form-group">
            <label for="category_remove">Cantidad: </label>
            <input id="category_remove" type="number" min="0" required placeholder=" " v-model.number="amount">
          </div>
          <div class="__form-group">
            <span>
              Precio unitario: <strong> ${{ unitCost }} </strong>
            </span>
          </div>
          <div class="__form-group">
            <span>
              Costo total: <strong> ${{ totalCost }} </strong>
            </span>
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
            <button class="__button __button-success" @click.prevent="create">Vender</button>
            <button class="__button __button-secondary" @click.prevent="clean">Limpiar campos</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import ProductService from '@/services/ProductService'
import SalesService from '@/services/SalesService'

export default {
  name: 'SalesComponent',
  data() {
    return {
      product: '',
      amount: 0,
      sale:'',
      unitCost: 0,
      totalCost: 0,
      success: undefined,
      error: undefined,
      products: []
    }
  },
  watch: {
    product() {
      this.unitCost = this.product.cost || 0
      this.totalCost = this.unitCost * this.amount
    },
    amount() {
      this.totalCost = this.unitCost * this.amount
    }
  },
  methods: {
    create() {
      if ( this.product && this.amount >= 0 ) {
        this.product.currentAmount=this.amount;
        const sale = {
          product: this.product,
          amount: this.totalCost
        }

        SalesService.create(sale)
          .then(resolve => {
            this.success = "Venta ingresada"
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
    clean() {
      this.product = ''
      this.amount = 0
    },
  },
  created() {
    ProductService.retrieveAll()
      .then(resolve => {
        this.products = resolve
      })
  }
}
</script>

