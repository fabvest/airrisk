<template>
  <div id="app">
    <vueform></vueform>
  </div>
</template>
<script>
export default {
  name: 'app',
  components: {
    'vueform': require('@/components/form/form.vue')
  }
}
</script>
<style lang="css">
  @import url('assets/css/bootstrap-grid.css');
  @import url('assets/css/bootstrap-reboot.min.css');
  @import url('assets/css/bootstrap.min.css');
  @import url('assets/css/main.css');
</style>
<!--<script src="/assets/js/script.js"></script>-->
<!--<script src="/assets/js/bootstrap.bundle.js"></script>-->
<!--<script>-->
  <!--require('assets/js/bootstrap.min.js');-->
  <!--require('assets/js/script.js');-->
  <!--require('assets/js/bootstrap.bundle.js');-->
<!--</script>-->
