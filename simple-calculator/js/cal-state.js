/*
A simple calculator, based on finite automation machine. 
For fun & exercise.
2013-03-22

digits = 0|1|2|3|4|5|6|7|8|9|.

    input      result     operator
s0  ''         ''         ''
s1  digits...  ''         ''
s2  ''         digits     +|-|*|/ 
s3  digits...  digits     +|-|*|/
s4  ''         digits     ''


state transition diagram:
          s0  s1  s2  s3  s4
digits    s1  s1  s3  s3  s1
operator  s0  s2  s2  s2  s2
clear     s0  s0  s0  s0  s0
=         s0  s4  s4  s4  s4


*/
var Calculator = {
    INPUTSTR: '0123456789.+-*/=C',
    display: document.querySelector("#display b"),
    state: 0,
    input: '',
    result: '',
    operator: '',
    inputDot: false,

    isDigit : function isDigit(value) {
	return (value === '.') || 
	       ((value >= '0') && (value <= '9'));
    },

    isOperator : function isOperator(value) {
	return value === '+' ||
	       value === '-' ||
	       value === '*' ||
	       value === '/';
    },

    clear: function clear() {
	this.state = 0;
	this.input = '';
	this.result = '';
	this.operator = '';
	this.inputDot = false;
    },

    updateDisplay: function updateDisplay() {
	var value = this.input || this.result || '0';
	this.display.textContent = value;
    },

    calculate: function calculate() {
	var tmp = 0;
	var result = parseFloat(this.result);
	var input = parseFloat(this.input);
	switch(this.operator) {
	    case '+':
	      tmp = result + input;
	      break;
	    case '-':
	      tmp = result - input;
	      break;
	    case '*':
	      tmp = result * input;
	      break;
	    case '/':
	      tmp = result / input;
	      break;
	}
	this.result = tmp;
	this.input = '';
	this.inputDot = false;
    },

    appendDigit : function appendDigit(value) {
	if(value === '.') {
	    if(this.inputDot) return;
	    if(!this.input)
		this.input += '0';
	    this.inputDot = true;
	}
	this.input += value;
    },

    s0 : function s0(value) {
	if(this.isDigit(value)) {
	    this.appendDigit(value);
	    this.state = 1;
	    }
	this.updateDisplay();
    },

    s1 : function s1(value) {
	if(this.isDigit(value)) {
	    this.appendDigit(value);
        } else if(this.isOperator(value)) {
	    this.operator = value;
	    this.result = this.input;
	    this.input = '';
	    this.state = 2;
	} else if(value === 'C') {
	    this.clear();
	    this.state = 0;
	} else if(value === '='){
	    this.result = this.input;
	    this.input = '';
	    this.state = 4;
	}
	this.updateDisplay();
    },
   
    s2 : function s2(value) {
	if(this.isDigit(value)) {
	    this.appendDigit(value);
	    this.state = 3;
	} else if(this.isOperator(value)) {
	    this.operator = value;
	} else if(value === 'C') {
	    this.clear();
	    this.state = 0;
	} else if(value === '=') {
	    this.operator = '';
	    this.state = 4;
	}
	this.updateDisplay();
    },

    s3 : function s3(value) {
	if(this.isDigit(value)) {
	    this.appendDigit(value);
	} else if(this.isOperator(value)) {
	    this.calculate();
	    this.operator = value;
	    this.state = 2;
	} else if(value === 'C') {
	    this.clear();
	    this.state = 0;
	} else if(value === '=') {
	    this.calculate();
	    this.operator = '';
	    this.state = 4;
	}
	this.updateDisplay();
    },

    s4 : function s4(value) {
	if(this.isDigit(value)) {
	    this.result = '';
	    this.appendDigit(value);
	    this.state = 1;
	} else if(this.isOperator(value)) {
	    this.operator = value;
	    this.state = 2;
	} else if(value === 'C') {
	    this.clear();
	    this.state = 0;
	} else if(value === '=') {
	}
	this.updateDisplay();
    },

    go : function(value) {
	switch(this.state) {
	    case 0:
	      this.s0(value);
	      break;
	    case 1:
	      this.s1(value);
	      break;
	    case 2:
	      this.s2(value);
	      break;
	    case 3:
	      this.s3(value);
	      break;
	    case 4:
	      this.s4(value);
	      break;
	    }
    },

    handleEvent : function handleEvent(evt) {
	var target = evt.target;
	var value = target.value;
	if(this.INPUTSTR.indexOf(value) != -1)
	    this.go(value);
    },

    init : function init() {
	document.addEventListener('mousedown', this);
	this.updateDisplay();
    }
}

window.onload=Calculator.init();
