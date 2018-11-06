<template>
  <div class="__col">
    <div class="__row">
      <div class="__col __col-3 __col-offset-1">
        <article class="__card __text-center">
          <router-link to="/category" class="__button __button-link">
            <h3>Categoría</h3>
          </router-link>
        </article>
      </div>
      <div class="__col __col-3 __col-offset-2">
        <article class="__card __text-center">
          <router-link to="/provider" class="__button __button-link">
            <h3>Proveedor</h3>
          </router-link>
        </article>
      </div>
    </div>
    <div class="__row">
      <div class="__col __col-3 __col-offset-1">
        <article class="__card __text-center">
          <router-link to="/product" class="__button __button-link">
            <h3>Producto</h3>
          </router-link>
        </article>
      </div>
      <div class="__col __col-3 __col-offset-2">
        <article class="__card __text-center">
          <router-link to="/sales" class="__button __button-link">
            <h3>Vender</h3>
          </router-link>
        </article>
      </div>
    </div>
    <div class="__row">
      <div class="__col __col-3 __col-offset-1">
        <article class="__card __text-center">
          <router-link to="/models" class="__button __button-link">
            <h3>Modelos</h3>
          </router-link>
        </article>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'IndexComponent'
}
</script>
