<template>
  <form class="container">
    <h2>Место нахождения</h2>
    <div class="form-group row">
      <label class="col-md-5">Организация</label>
      <select v-model="org" class="form-control col-md-7">
        <option selected disabled>Выберите...</option>
        <option v-for="org in arrayOrg" v-bind:value="org">{{ org }}</option>
      </select>
    </div>
    <div class="form-group row">
      <label class="col-md-5" for="city">Город</label>
      <input v-model="city" id="city" class="form-control col-md-7" type="text">
    </div>
    <div class="form-group row">
      <label class="col-md-5" for="district">Район</label>
      <input v-model="district" id="district" class="form-control col-md-7" type="text">
    </div>
    <div class="form-group row">
      <label class="col-md-5" for="street">Улица</label>
      <input v-model="street" id="street" class="form-control col-md-7" type="text">
    </div>
    <div class="form-group row">
      <label class="col-md-5" for="house">Дом</label>
      <input v-model="house" id="house" class="form-control col-md-7" type="number">
    </div>
    <div class="form-group row">
      <label class="col-md-5" for="category">Категория населения</label>
      <select v-model="catPeople" id="category" class="form-control col-md-7">
        <option selected disabled>Выберите...</option>
        <option v-for="cat in arrayCatPeople" v-bind:value="cat.value">{{ cat.text }}</option>
      </select>
    </div>
    <hr>
    <div class="row">
      <h2>Вещества</h2>
      <button v-on:click="addDrug()" type="button" class="btn btn-primary">Добавить вещество</button>
    </div>
    <drug v-for="index in arraySelectForm" @removeDrug="removeDrugIndex(index)" ref="customSelect"></drug>
    <h2>Период</h2>
    <div class="form-group row">
      <!-- Месяц -->
      <label class="col-md-5">Начало периода</label>
      <select v-model="startPerMonth" id="start_per_unit" class="form-control col-md-3">
        <option selected disabled>Выберите месяц...</option>
        <option value="1">месяц</option>
        <option value="2">год</option>
      </select>
      <!-- Год -->
      <select v-model="startPerYear" id="start_per_value" class="form-control col-md-3">
        <option selected disabled>Выберите год...</option>
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
      <select v-model="endPerMonth" id="end_per_unit" class="form-control col-md-3">
        <option selected>Конец периода</option>
        <option value="1">месяц</option>
        <option value="2">год</option>
      </select>
      <select v-model="endPerYear" id="end_per_value" class="form-control col-md-3">
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
      <button v-on:click="submit()" type="button" class="btn btn-primary btn-lg btn-block">Рассчитать</button>
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
        org: null,
        city: null,
        district: null,
        street: null,
        house: null,
        catPeople: null,
        startPerMonth: null,
        startPerYear: null,
        endPerMonth: null,
        endPerYear: null,
        arrayOrg: [
          'Роспотребнадзор по Пензе',
          'Роспотребнадзор по Самаре'
        ],
        arrayCatPeople: [
          { value: 'Дети (до 14 лет) -1', text: 'Дети (до 14 лет)' },
          { value: 'Подростки (от 14 до 17 лет) -2', text: 'Подростки (от 14 до 17 лет)' },
          { value: 'Взрослые (от 18 лет) -3', text: 'Взрослые (от 18 лет)' }
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
      },
      submit () {
        let formValue = {}
        // Set value array drag
        let arrayDrag = []
        _.forEach(this.arraySelectForm, (value, key) => {
          const formValue = this.$refs.customSelect[key].submit()
          arrayDrag.push(formValue)
        })
        formValue.arrayDrag = arrayDrag
        // Set location info
        formValue.org = this.org
        formValue.city = this.city
        formValue.district = this.district
        formValue.street = this.street
        formValue.house = this.house
        formValue.catPeople = this.catPeople
        formValue.startPerMonth = this.startPerMonth
        formValue.startPerYear = this.startPerYear
        formValue.endPerMonth = this.endPerMonth
        formValue.endPerYear = this.endPerYear
        console.log('submit!', formValue)
      }
    }
  }
</script>
