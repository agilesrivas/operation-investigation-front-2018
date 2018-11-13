<style>
#app {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 5;
}
</style>

<template>
  <main id="app" class="__full-height-perc __overflow-hidden">
    <div class="__mininav">
      <router-link to="/" class="__button __button-link __button-rounded __to-home">
        <i class="fas fa-home"></i>
      </router-link>
      <router-link to="/config" class="__button __button-link __button-rounded __to-home">
        <i class="fas fa-cogs"></i>
      </router-link>
    </div>
    <router-view></router-view>
  </main>
</template>

<script>
export default {
  name: 'app'
}
</script>