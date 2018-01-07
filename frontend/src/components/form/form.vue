<template>
  <div>
    <button v-on:click="addForm()">Add new select</button>
    <custom-select
      v-for="index in arraySelectForm"
      v-bind:arrayOption="arrayOption"
      ref="customSelect"
    ></custom-select>
    <button v-on:click="submit()">submit</button>
  </div>
</template>
<script>
  var _ = require('lodash')
  export default {
    components: {
      'custom-select': require('@/components/shared/custom-select/custom-select.vue')
    },
    data () {
      return {
        arrayOption: [
          { id: 1, text: 'first point' },
          { id: 2, text: 'second point' },
          { id: 3, text: 'third point' }
        ],
        arraySelectForm: [ 0 ]
      }
    },
    methods: {
      addForm () {
        this.arraySelectForm.push(this.arraySelectForm.length)
      },
      submit () {
        // console.log(this.$refs.customSelect)
        let arrayFormValues = []
        _.forEach(this.arraySelectForm, (value, key) => {
          const formValue = this.$refs.customSelect[key].submit()
          arrayFormValues.push(formValue)
        })
        console.log('submit!', arrayFormValues)
      }
    }
  }
</script>
