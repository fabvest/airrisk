<template>
<div>
  <div class="form-group row">
    <label class="col-md-5" for="drug">Наименование вещества</label>
    <select v-model="drug" id="drug" class="form-control col-md-7">
      <option selected disabled>Выберите...</option>
      <option v-for="drug in arrayDrug" v-bind:value="drug.id">{{ drug.text }}</option>
    </select>
  </div>
  <div class="form-group row">
    <label class="col-md-5" for="unit">Единица измерения</label>
    <select v-model="unit" id="unit" class="form-control col-md-7" required>
      <option selected disabled>Выберите...</option>
      <option v-for="unit in arrayUnit" v-bind:value="unit.id">{{ unit.text }}</option>
    </select>
  </div>
  <div class="form-group row">
    <label class="col-md-5" for="concentration">Концентрация вещества</label>
    <input v-model="concentration" id="concentration" class="form-control col-md-7" type="number" required>
  </div>
  <button v-on:click="onRemoveForm()" class="btn btn-primary" type="button">Удалить вещество</button>
  <hr>
</div>
</template>
<script>
  export default {
    data () {
      return {
        drug: null,
        unit: null,
        concentration: null,
        arrayDrug: [
          { id: 1, text: 'Морфий' },
          { id: 2, text: 'Свинец' },
          { id: 3, text: 'Амиак' }
        ],
        arrayUnit: [
          { id: 1, text: 'мг/см3' },
          { id: 2, text: 'г/м3' },
          { id: 3, text: 'мкг/м3' }
        ]
      }
    },
    methods: {
      onRemoveForm () {
        this.$emit('removeDrug')
      },
      submit () {
        return {
          drug: this.drug,
          unit: this.unit,
          concentration: Number(this.concentration)
        }
      }
    }
  }
</script>
