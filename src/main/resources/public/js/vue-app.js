function CreateFormControl() {
  // props
  this.value = null;
  this.isValid = false;
  this.isShowErrorMessage = false;
  var isTouch = false;
  var isFocus = false;

  // methods
  this.onFocus = () => {
    isTouch = isFocus = true;
  };
  this.onBlur = () => {
    isFocus = false;
    this.validator();
  };
  this.validator = () => {
    this.isValid = !!this.value;
    this.isShowErrorMessage = !this.isValid && !isFocus && isTouch;
    return this.isValid;
  };
}

function createWatchValue(nameControl) {
  return function (newValue, oldValue) {
    if (!oldValue && newValue) {
      this[nameControl].isTouch = true;
    }
    this[nameControl].isValid = !!newValue;
  };
}

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
      if (this.searchValue.length < 0 || this.currentValue && this.searchValue === this.currentValue['IUPAC']) return [];
      const arraySearchValue = _.filter(this.arrayObject, (drug) => drug['IUPAC'].toLowerCase().indexOf(this.searchValue.toLowerCase()) !== -1);
      return this.searchValue.length ? arraySearchValue : [];
    },
    isShowList: function () {
      return (this.isFocus && !!this.searchValue.length) || this.isFocus;
    },
    selectedValue: function(object) {
      this.currentValue = object;
      this.searchValue = object['IUPAC'];
    },
    onTestValid: function () {
      if (!this.isTouch) return;
      if (this.currentValue && this.currentValue['IUPAC'] !== this.searchValue) {
        this.currentValue = null;
      }
      this.isValid = !!this.currentValue;
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
    },
    onSubmit: function () {
      return this.currentValue;
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
        {{ item.IUPAC }}
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
      unitControl: new CreateFormControl('unitControl'),
      concentrationControl: new CreateFormControl('concentrationControl'),
      arrayDrug: [],
      arrayUnit: [
        { id: 1, text: 'мг/см3' },
        { id: 2, text: 'г/м3' },
        { id: 3, text: 'мкг/м3' }
      ],
      errorMessageDrug: 'Вы не выбрали вещество.'
    }
  },
  created () {
    this.$http.get('drugs.json').then(
      (data) => {
        this.arrayDrug = data.body.data;
      },
      (error) => console.error(error)
    );
  },
  watch: {
    'unitControl.value': createWatchValue('unitControl'),
    'concentrationControl.value': createWatchValue('concentrationControl')
  },
  methods: {
    onRemoveForm () {
      this.$emit('removeDrug');
    },
    isValidControls() {
      return this.unitControl.value && this.concentrationControl.value && this.$refs['drug-autocomplete'].onSubmit();
    },
    submit() {
      if (!this.isValidControls()) return null;
      return {
        drug: this.$refs['drug-autocomplete'].onSubmit().id,
        unit: this.unitControl.value,
        concentration: Number(this.concentrationControl.value)
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
    <select
    v-model="unitControl.value"
    v-on:focus="unitControl.onFocus()"
    v-on:blur="unitControl.onBlur()"
    v-bind:class="{ 'is-valid': unitControl.isValid, 'is-invalid': unitControl.isShowErrorMessage }"
    class="form-control col-md-7"
    id="unit"
    required
    >
      <option selected disabled>Выберите...</option>
      <option v-for="unit in arrayUnit" v-bind:value="unit.id">{{ unit.text }}</option>
    </select>
    <p v-bind:class="{ 'display-block': unitControl.isShowErrorMessage }" class="invalid-feedback">
      Вы не выбрали единицы измерения.
    </p>
  </div>
  <div class="form-group row">
    <label class="col-md-5" for="concentration">Концентрация вещества</label>
    <input
    v-model="concentrationControl.value"
    v-on:focus="concentrationControl.onFocus()"
    v-on:blur="concentrationControl.onBlur()"
    v-bind:class="{ 'is-valid': concentrationControl.isValid, 'is-invalid': concentrationControl.isShowErrorMessage }"
    class="form-control col-md-7"
    id="concentration"
    type="number"
    required
    >
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
      orgControl: new CreateFormControl('orgControl'),
      cityControl: new CreateFormControl('cityControl'),
      districtControl: new CreateFormControl('districtControl'),
      streetControl: new CreateFormControl('streetControl'),
      houseControl: new CreateFormControl('houseControl'),
      catPeopleControl: new CreateFormControl('catPeopleControl'),
      startPerMonthControl: new CreateFormControl('startPerMonthControl'),
      startPerYearControl: new CreateFormControl('startPerYearControl'),
      endPerMonthControl: new CreateFormControl('endPerMonthControl'),
      endPerYearControl: new CreateFormControl('endPerYearControl'),
      dateControl: new CreateFormControl('dateControl'),
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
  watch: {
    'orgControl.value': createWatchValue('orgControl'),
    'cityControl.value': createWatchValue('cityControl'),
    'districtControl.value': createWatchValue('districtControl'),
    'streetControl.value': createWatchValue('streetControl'),
    'houseControl.value': createWatchValue('houseControl'),
    'catPeopleControl.value': createWatchValue('catPeopleControl'),
    'startPerMonthControl.value': createWatchValue('startPerMonthControl'),
    'startPerYearControl.value': createWatchValue('startPerYearControl'),
    'endPerMonthControl.value': createWatchValue('endPerMonthControl'),
    'endPerYearControl.value': createWatchValue('endPerYearControl'),
    'dateControl.value': createWatchValue('dateControl')
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
        (error) => console.error(error) // error
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
      formValue.orgControl = this.orgControl.value;
      formValue.cityControl = this.cityControl.value;
      formValue.districtControl = this.districtControl.value;
      formValue.streetControl = this.streetControl.value;
      formValue.houseControl = Number(this.houseControl.value);
      formValue.catPeopleControl = this.catPeopleControl.value;
      formValue.startPerMonthControl = Number(this.startPerMonthControl.value) + 1;
      formValue.startPerYearControl = Number(this.startPerYearControl.value);
      formValue.endPerMonthControl = Number(this.endPerMonthControl.value) + 1;
      formValue.endPerYearControl = Number(this.endPerYearControl.value);
      formValue.dateControl = this.dateControl.value;
      // post data
      console.log(_.clone(formValue));
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
      <select
        v-model="orgControl.value"
        v-on:focus="orgControl.onFocus()"
        v-on:blur="orgControl.onBlur()"
        v-bind:class="{ 'is-invalid': orgControl.isShowErrorMessage, 'is-valid': orgControl.isValid }"
        class="form-control col-md-7"
        required
      >
        <option selected disabled>Выберите...</option>
        <option v-for="org in arrayOrg" v-bind:value="org">{{ org }}</option>
      </select>
    </div>
    <div class="form-group row">
      <label class="col-md-5" for="city">Город</label>
      <input
        v-model="cityControl.value"
        v-on:focus="cityControl.onFocus()"
        v-on:blur="cityControl.onBlur()"
        v-bind:class="{ 'is-invalid': cityControl.isShowErrorMessage, 'is-valid': cityControl.isValid }"
        class="form-control col-md-7"
        id="city"
        type="text"
        required
        >
    </div>
    <div class="form-group row">
      <label class="col-md-5" for="district">Район</label>
      <input
        v-model="districtControl.value"
        v-on:focus="districtControl.onFocus()"
        v-on:blur="districtControl.onBlur()"
        v-bind:class="{ 'is-invalid': districtControl.isShowErrorMessage, 'is-valid': districtControl.isValid }"
        class="form-control col-md-7"
        id="district"
        type="text"
        required
      >
    </div>
    <div class="form-group row">
      <label class="col-md-5" for="street">Улица</label>
      <input
        v-model="streetControl.value"
        v-on:focus="streetControl.onFocus()"
        v-on:blur="streetControl.onBlur()"
        v-bind:class="{ 'is-invalid': streetControl.isShowErrorMessage, 'is-valid': streetControl.isValid }"
        class="form-control col-md-7"
        id="street"
        type="text"
        required
      >
    </div>
    <div class="form-group row">
      <label class="col-md-5" for="house">Дом</label>
      <input
        v-model="houseControl.value"
        v-on:focus="houseControl.onFocus()"
        v-on:blur="houseControl.onBlur()"
        v-bind:class="{ 'is-invalid': houseControl.isShowErrorMessage, 'is-valid': houseControl.isValid }"
        class="form-control col-md-7"
        id="house"
        type="number"
        required
      >
    </div>
    <div class="form-group row">
      <label class="col-md-5" for="category">Категория населения</label>
      <select
        v-model="catPeopleControl.value"
        v-on:focus="catPeopleControl.onFocus()"
        v-on:blur="catPeopleControl.onBlur()"
        v-bind:class="{ 'is-invalid': catPeopleControl.isShowErrorMessage, 'is-valid': catPeopleControl.isValid }"
        class="form-control col-md-7"
        id="category"
        required
      >
        <option selected disabled>Выберите...</option>
        <option v-for="cat in arrayCatPeople" v-bind:value="cat.value">{{ cat.text }}</option>
      </select>
    </div>
    <hr>
    <div class="container row title-group">
      <h2>Вещества</h2>
      <button v-on:click="addDrug()" type="button" class="btn btn-primary title-button">Добавить вещество</button>
    </div>
    <v-drug-control v-for="index in arraySelectForm" @removeDrug="removeDrugIndex(index)" ref="customSelect"></v-drug-control>
    <h2>Период</h2>
    <div class="form-group row">
      <label class="col-md-5">Начало периода</label>
      <select
        v-model="startPerMonthControl.value"
        v-on:focus="startPerMonthControl.onFocus()"
        v-on:blur="startPerMonthControl.onBlur()"
        v-bind:class="{ 'is-invalid': startPerMonthControl.isShowErrorMessage, 'is-valid': startPerMonthControl.isValid }"
        id="start_per_unit"
        class="form-control col-md-3"
        required
      >
        <option selected disabled>Выберите месяц...</option>
        <option v-for="(month, index) in arrayMonth" v-bind:value="index">{{ month }}</option>
      </select>
      <input
        v-model="startPerYearControl.value"
        v-on:focus="startPerYearControl.onFocus()"
        v-on:blur="startPerYearControl.onBlur()"
        v-bind:class="{ 'is-invalid': startPerYearControl.isShowErrorMessage, 'is-valid': startPerYearControl.isValid }"
        class="form-control col-md-3 offset-md-1"
        type="number"
        placeholder="Введите год..."
        >
    </div>
    <div class="form-group row">
      <label class="col-md-5">Конец периода</label>
      <select
        v-model="endPerMonthControl.value"
        v-on:focus="endPerMonthControl.onFocus()"
        v-on:blur="endPerMonthControl.onBlur()"
        v-bind:class="{ 'is-invalid': endPerMonthControl.isShowErrorMessage, 'is-valid': endPerMonthControl.isValid }"
        class="form-control col-md-3"
        id="end_per_unit"
        required
      >
        <option selected disabled>Выберите месяц...</option>
        <option v-for="(month, index) in arrayMonth" v-bind:value="index">{{ month }}</option>
      </select>
      <input
        v-model="endPerYearControl.value"
        v-on:focus="endPerYearControl.onFocus()"
        v-on:blur="endPerYearControl.onBlur()"
        v-bind:class="{ 'is-invalid': endPerYearControl.isShowErrorMessage, 'is-valid': endPerYearControl.isValid }"
        class="form-control col-md-3 offset-md-1"
        type="number"
        placeholder="Введите год..."
      >
    </div>
    <div class="form-group row">
      <label class="col-md-5" for="date">Дата</label>
      <input
        v-model="dateControl.value"
        v-on:focus="dateControl.onFocus()"
        v-on:blur="dateControl.onBlur()"
        v-bind:class="{ 'is-invalid': dateControl.isShowErrorMessage, 'is-valid': dateControl.isValid }"
        class="form-control col-md-7"
        type="date"
        id="date"
        required
      >
    </div>
    <div>
      <button v-on:click="submit()" type="button" class="btn btn-primary btn-lg btn-block">Рассчитать</button>
    </div>
  </form>
`
});