<template>
  <div id="app">
    <layout></layout>
    <vueform></vueform>
  </div>
</template>
<script>
export default {
  name: 'app',
  components: {
    'layout': require('@/components/layout/layout.vue'),
    'vueform': require('@/components/form/form.vue')
  }
}
</script>
<style lang="scss">
  @import url('styles/index.scss');
</style>
