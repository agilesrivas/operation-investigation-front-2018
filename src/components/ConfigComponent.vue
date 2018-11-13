<template>
  <div class="__row __full-height-perc">
    <div id="form-container" class="__col __col-offset-3 __col-4 __align-self-center">
      <form action="" class="__card">
        <header class="__form-header">
          <h2 class="__title">Configuración</h2>
          <p class="__description">Aqui podrás asignarles valores a algunas variables necesarias para el sistema.</p>
        </header>
        <div class="__form-body">
          <input type="hidden" required v-model="id">
          <div class="__form-group __column">
            <label for="maintenance">Costo de Mantenimiento: </label>
            <input id="maintenance" type="number" min="0" required placeholder=" " v-model.number="maintenance">
          </div>
          <div class="__form-group __column">
            <label for="work_days">Cantidad días laborales anual: </label>
            <input id="work_days" type="number" min="0" max="366" required placeholder=" " v-model="work_days">
          </div>
          <div class="__form-group __column">
            <label for="days">Días de una compra a otra: </label>
            <input id="days" type="number" min="0" required placeholder=" " v-model.number="days">
          </div>
          <div class="__form-group __column">
            <label for="days">Costo de pedidos a realizar: </label>
            <input id="pedidos" type="number" min="0" required placeholder=" " v-model="costPurchase">
          </div>
          <div class="__form-group __column">
            <label for="days">Porcentaje de Servicio: </label>
            <input id="service" type="number" min="0" required placeholder=" " v-model="porcent">
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
            <button class="__button __button-success" @click.prevent="confirm">Confirmar</button>
            <button class="__button __button-secondary" @click.prevent="clean">Limpiar campos</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import ConfigService from '@/services/ConfigService'

export default {
  name: 'ConfigComponent',
  data() {
    return {
      id: 1,
      maintenance: 0,
      work_days: 365,
      days: 0,
      porcent:0,
      costPurchase:0
    }
  },
  methods: {
    confirm() {
      if ( (this.id === 1) && (this.maintenance >= 0) && (this.work_days >= 0) && (366 >=this.days > 0)) {
        const config = {
          id: this.id,
          costoMantenimiento: this.maintenance,
          costoVenta:this.costPurchase,
          porcentajeServicio:this.porcent,
          diasLaborales: this.work_days,
          diasDeCompras: this.days
        }

        ConfigService.update(config)
          .then(resolve => {
            this.success = resolve

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
    }
  }
}
</script>

