<template>
  <form class="container">
    <h2>Место нахождения</h2>
    <div class="form-group row">
      <label class="col-md-5" for="org">Организация</label>
      <select name="org" id="org" class="form-control col-md-7">
        <option selected>Выберите...</option>
        <option value="1">Роспотребнадзор по Самаре</option>
        <option value="2">Роспотребнадзор по Пензе</option>
      </select>
    </div>
    <div class="form-group row">
      <label class="col-md-5" for="city">Город</label>
      <input name="city" id="city" class="form-control col-md-7"/>
    </div>
    <div class="form-group row">
      <label class="col-md-5" for="district">Район</label>
      <input name="district" id="district" class="form-control col-md-7"/>
    </div>
    <div class="form-group row">
      <label class="col-md-5" for="street">Улица</label>
      <input name="street" id="street" class="form-control col-md-7"/>
    </div>
    <div class="form-group row">
      <label class="col-md-5" for="house">Дом</label>
      <input name="house" id="house" class="form-control col-md-7"/>
    </div>
    <div class="form-group row">
      <label class="col-md-5" for="category">Категория населения</label>
      <select name="category" id="category" class="form-control col-md-7">
        <option selected>Выберите...</option>
        <option value="Дети (до 14 лет) -1">Дети (до 14 лет)</option>
        <option value="Подростки (от 14 до 17 лет) -2">Подростки (от 14 до 17 лет)</option>
        <option value="Взрослые (от 18 лет) -3">Взрослые (от 18 лет)</option>
      </select>
    </div>
    <hr>
    <div class="row">
      <h2>Вещества</h2>
      <button v-on:click="addDrug()" type="button" class="btn btn-primary">Добавить вещество</button>
    </div>
    <drug v-for="index in arraySelectForm" v-bind:indexForm="index" @removeDrug="removeDrugIndex(index)"></drug>
    <hr>
    <h2>Период</h2>
    <div class="form-group row">
      <label class="col-md-5">Начало периода</label>
      <select name="start_per_unit" id="start_per_unit" class="form-control col-md-3">
        <option selected>Начало периода</option>
        <option value="1">месяц</option>
        <option value="2">год</option>
      </select>
      <select name="start_per_value" id="start_per_value" class="form-control col-md-3">
        <option selected>Начало периода</option>
        <option value="1">Роспотребнадзор по Самаре</option>
        <option value="2">Роспотребнадзор по Пензе</option>
        <option value="3">Роспотребнадзор по Пензе</option>
        <option>Роспотребнадзор по Пензе</option>
        <option>...</option>
        <option>...</option>
      </select>
    </div>
    <div class="form-group row">
      <label class="col-md-5">Конец периода</label>
      <select name="start_per_unit" id="end_per_unit" class="form-control col-md-3">
        <option selected>Конец периода</option>
        <option value="1">месяц</option>
        <option value="2">год</option>
      </select>
      <select name="start_per_value" id="end_per_value" class="form-control col-md-3">
        <option selected>Конец периода</option>
        <option value="1">Роспотребнадзор по Самаре</option>
        <option value="2">Роспотребнадзор по Пензе</option>
        <option value="3">Роспотребнадзор по Пензе</option>
        <option>Роспотребнадзор по Пензе</option>
        <option>...</option>
        <option>...</option>
      </select>
    </div>
    <div class="form-group row">
      <label class="col-md-5" for="date">Дата</label>
      <input type="date" name="date" id="date" class="form-control col-md-5"/>
    </div>
    <div>
      <button type="submit" class="btn btn-primary btn-lg btn-block">Рассчитать</button>
    </div>
  </form>
</template>

<script>
  var _ = require('lodash')
  export default {
    components: {
      'drug': require('@/components/form/drug/drug.vue')
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
      addDrug () {
        this.arraySelectForm.push(this.arraySelectForm.length)
      },
      removeDrugIndex: function (index) {
        this.arraySelectForm = _.remove(this.arraySelectForm, item => item === index)
        console.log('removeDrugIndex value', index)
      },
      submit () {
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
