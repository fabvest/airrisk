<template>
  <div>
    <select v-model="formValue">
      <option v-for="option in arrayOption" :value="option">
        {{ option.text }}
      </option>
    </select>
  </div>
</template>
<script>
  var _ = require('lodash')
  export default {
    props: [ 'arrayDrug' ],
    data () {
      return {
        formValue: {}
      }
    },
    methods: {
      submit () {
        if (_.isEmpty(this.formValue)) return {}
        return this.formValue
      }
    }
  }
</script>

