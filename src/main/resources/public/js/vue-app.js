var AutocompleteControlForm = {
  props: [ 'arrayObject', 'errorMessage' ],
  data : function () {
    return {
      currentValue: null,
      searchValue: '',
      isTouch: false,
      isValid: false,
      isFocus: false
    }
  },
  methods: {
    getArraySearchValue: function () {
      if (this.searchValue.length < 0 || this.currentValue && this.searchValue === this.currentValue.text) return [];
      const arraySearchValue = _.filter(this.arrayObject, (drug) => drug.text.toLowerCase().indexOf(this.searchValue.toLowerCase()) !== -1);
      return this.searchValue.length ? arraySearchValue : [];
    },
    isShowList: function () {
      return (this.isFocus && !!this.searchValue.length) || this.isFocus;
    },
    selectedValue: function(object) {
      this.currentValue = object;
      this.searchValue = object.text;
    },
    onTestValid: function () {
      if (!this.isTouch) return;
      this.isValid = !!this.currentValue && this.currentValue.text === this.searchValue;
    },
    onBlurInput: function () {
      setTimeout(() => {
        this.isFocus = false;
        this.onTestValid();
      }, 200);
    },
    onFocusInput: function () {
      this.isFocus = this.isTouch = true;
    },
    showErrorMessage: function () {
      return !this.isValid && this.searchValue.length && !this.isFocus && this.isTouch;
    }
  },
  template: `
    <div class="autocomplete">
      <input
        v-model="searchValue"
        v-on:focus="onFocusInput()"
        v-on:blur="onBlurInput()"
        v-bind:class="{ 'is-invalid': showErrorMessage(), 'is-valid': isValid }"
        class="form-control"
        type="text"
        placeholder="введите..."
      >
      <ul v-if="isShowList()" class="list-group autocomplete__list">
        <li
          v-for="item in getArraySearchValue()" 
          v-on:click="selectedValue(item)"
          class="list-group-item list-group-item-action"
        >
        {{ item.text }}
        </li>
      </ul>
      <p v-bind:class="{ 'display-block': showErrorMessage() }" class="invalid-feedback">
        {{ errorMessage }}
      </p>
    </div>
  `
};

var DrugControlForm = {
  data () {
    return {
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
      ],
      errorMessageDrug: 'Вы не выбрали вещество.'
    }
  },
  methods: {
    onRemoveForm () {
      this.$emit('removeDrug');
    },
    submit () {
      return {
        drug: this.$refs['drug-autocomplete'].currentValue.id,
        unit: this.unit,
        concentration: Number(this.concentration)
      }
    }
  },
  components: {
    'v-autocomplete': AutocompleteControlForm
  },
  template: `
  <div>
  <div class="form-group row">
    <label class="col-md-5">Наименование вещества</label>
    <v-autocomplete v-bind:array-object="arrayDrug" v-bind:errorMessage="errorMessageDrug" ref="drug-autocomplete" class="col-md-7"></v-autocomplete>
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
  `
};
new Vue({
  el: '#v-form',
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
      date: null,
      arrayOrg: [
        'Роспотребнадзор по Пензе',
        'Роспотребнадзор по Самаре'
      ],
      arrayCatPeople: [
        { value: 'Дети (до 14 лет) -1', text: 'Дети (до 14 лет)' },
        { value: 'Подростки (от 14 до 17 лет) -2', text: 'Подростки (от 14 до 17 лет)' },
        { value: 'Взрослые (от 18 лет) -3', text: 'Взрослые (от 18 лет)' }
      ],
      arraySelectForm: [ 0 ],
      arrayMonth: ['Январь', 'Февраль', 'Март', 'Апрель', 'Май', 'Июнь', 'Июль', 'Август', 'Сентябрь', 'Октябь', 'Ноябрь', 'Декабрь']
    }
  },
  methods: {
    addDrug () {
      this.arraySelectForm.push(this.arraySelectForm.length);
    },
    removeDrugIndex: function (index) {
      this.arraySelectForm = _.remove(this.arraySelectForm, item => item === index);
    },
    postDataForm (data) {
      this.$http.post('/getinfo', data).then(
        (response) => console.log(response), // successful
        (responseError) => console.error(responseError) // error
      );
    },
    submit () {
      let formValue = {};
      // Set value array drag
      let arrayDrag = [];
      _.forEach(this.arraySelectForm, (value, key) => {
        const formValue = this.$refs.customSelect[key].submit();
        arrayDrag.push(formValue);
      });
      formValue.arrayDrag = arrayDrag;
      // Set location info
      formValue.org = this.org;
      formValue.city = this.city;
      formValue.district = this.district;
      formValue.street = this.street;
      formValue.house = Number(this.house);
      formValue.catPeople = this.catPeople;
      formValue.startPerMonth = Number(this.startPerMonth + 1);
      formValue.startPerYear = Number(this.startPerYear);
      formValue.endPerMonth = Number(this.endPerMonth + 1);
      formValue.endPerYear = Number(this.endPerYear);
      formValue.date = this.date;
      // post data
      this.postDataForm(formValue)
    }
  },
  components: {
    'v-drug-control': DrugControlForm
  },
  template: `
<form class="container">
    <h2>Место нахождения</h2>
    <div class="form-group row">
      <label class="col-md-5">Организация</label>
      <select v-model="org" class="form-control col-md-7" required>
        <option selected disabled>Выберите...</option>
        <option v-for="org in arrayOrg" v-bind:value="org">{{ org }}</option>
      </select>
    </div>
    <div class="form-group row">
      <label class="col-md-5" for="city">Город</label>
      <input v-model="city" id="city" class="form-control col-md-7" type="text" required>
    </div>
    <div class="form-group row">
      <label class="col-md-5" for="district">Район</label>
      <input v-model="district" id="district" class="form-control col-md-7" type="text" required>
    </div>
    <div class="form-group row">
      <label class="col-md-5" for="street">Улица</label>
      <input v-model="street" id="street" class="form-control col-md-7" type="text" required>
    </div>
    <div class="form-group row">
      <label class="col-md-5" for="house">Дом</label>
      <input v-model="house" id="house" class="form-control col-md-7" type="number" required>
    </div>
    <div class="form-group row">
      <label class="col-md-5" for="category">Категория населения</label>
      <select v-model="catPeople" id="category" class="form-control col-md-7" required>
        <option selected disabled>Выберите...</option>
        <option v-for="cat in arrayCatPeople" v-bind:value="cat.value">{{ cat.text }}</option>
      </select>
    </div>
    <hr>
    <div class="container row">
      <h2>Вещества</h2>
      <button v-on:click="addDrug()" type="button" class="btn btn-primary" style="margin-left: 20px;">Добавить вещество</button>
    </div>
    <v-drug-control v-for="index in arraySelectForm" @removeDrug="removeDrugIndex(index)" ref="customSelect"></v-drug-control>
    <h2>Период</h2>
    <div class="form-group row">
      <!-- Месяц -->
      <label class="col-md-5">Начало периода</label>
      <select v-model="startPerMonth" id="start_per_unit" class="form-control col-md-3" required>
        <option selected disabled>Выберите месяц...</option>
        <option v-for="(month, index) in arrayMonth" v-bind:value="index">{{ month }}</option>
      </select>
      <!-- Год -->
      <input v-model="startPerYear" type="number" class="form-control col-md-3 offset-md-1" placeholder="Введите год...">
    </div>
    <div class="form-group row">
      <label class="col-md-5">Конец периода</label>
      <select v-model="endPerMonth" id="end_per_unit" class="form-control col-md-3" required>
        <option selected disabled>Выберите месяц...</option>
        <option v-for="(month, index) in arrayMonth" v-bind:value="index">{{ month }}</option>
      </select>
      <input v-model="endPerYear" type="number" class="form-control col-md-3 offset-md-1" placeholder="Введите год...">
    </div>
    <div class="form-group row">
      <label class="col-md-5" for="date">Дата</label>
      <input type="date" v-model="date" id="date" class="form-control col-md-7" required>
    </div>
    <div>
      <button v-on:click="submit()" type="button" class="btn btn-primary btn-lg btn-block">Рассчитать</button>
    </div>
  </form>
`
});