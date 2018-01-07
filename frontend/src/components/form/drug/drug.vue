<template>
<div>
  <div class="form-group row">
    <label class="col-md-5" for="drug">Наименование вещества</label>
    <select name="drug" id="drug" class="form-control col-md-7">
      <option selected>Выберите...</option>
      <option value="Морфий">Морфий</option>
      <option value="Свинец">Свинец</option>
      <option value="Амиак">Амиак</option>
    </select>
  </div>
  <div class="form-group row">
    <label class="col-md-5" for="unit">Единица измерения</label>
    <select name="unit" id="unit" class="form-control col-md-7">
      <option selected>Выберите...</option>
      <option value="мг/см3">мг/см3</option>
      <option value="г/м3">г/м3</option>
      <option value="мкг/м3">мкг/м3</option>
    </select>
  </div>
  <div class="form-group row">
    <label class="col-md-5" for="concentration">Концентрация вещества</label>
    <input name="concentration" id="concentration" class="form-control col-md-7"/>
  </div>
  <button v-on:click="onRemoveForm()" class="btn btn-primary" type="button">Удалить вещество {{ indexForm }}</button>
  <hr>
</div>
</template>
<script>
  export default {
    props: [ 'indexForm' ],
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
      onRemoveForm () {
        this.$emit('removeDrug')
      }
    }
  }
</script>
