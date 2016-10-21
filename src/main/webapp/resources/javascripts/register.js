/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

new Vue({
    el: '#app',
    data: {
        email: '',
        firstname: '',
        lastname: '',
        password1: '',
        password2: '',
        shippingAddressLine1: '',
        shippingAddressLine2: '',
        shippingPostcode: '',
        shippingCity: '',
        shippingState: '',
        billingAddressLine1: '',
        billingAddressLine2: '',
        billingPostcode: '',
        billingCity: '',
        billingState: '',
        billingSameAsShippingAddress: true,
        valid: 'fa fa-fw valid-icon fa-check',
        invalid: 'fa fa-fw invalid-icon fa-exclamation-circle',
        emailFeedback: '',
        firstnameFeedback: '',
        lastnameFeedback: '',
        password1Feedback: '',
        password2Feedback: '',
        shippingCityFeedback: '',
        shippingStateFeedback: '',
        shippingAddressLine1Feedback: '',
        shippingPostcodeFeedback: '',
        shippingAddressLine2Feedback: '',
        billingCityFeedback: '',
        billingStateFeedback: '',
        billingAddressLine1Feedback: '',
        billingPostcodeFeedback: '',
        billingAddressLine2Feedback: '',
        validStates: ['act', 'nsw', 'qld', 'vic', 'tas', 'wa', 'sa', 'nt', 'australian capital territory', 'queensland', 'new south wales', 'south australia', 'western australia', 'tasmania', 'northern territory', 'victoria']
    },
    computed: {
        formIsValid: function () {
            var formDataIsValid = true;
            if (formDataIsValid && (this.emailFeedback !== this.valid)) {
                formDataIsValid = false;
            }
            if (formDataIsValid && (this.firstnameFeedback !== this.valid)) {
                formDataIsValid = false;
            }
            if (formDataIsValid && (this.lastnameFeedback !== this.valid)) {
                formDataIsValid = false;
            }
            if (formDataIsValid && (this.password1Feedback !== this.valid)) {
                formDataIsValid = false;
            }
            if (formDataIsValid && (this.password2Feedback !== this.valid)) {
                formDataIsValid = false;
            }
            if (formDataIsValid && (this.shippingAddressLine1Feedback !== this.valid)) {
                formDataIsValid = false;
            }
            if (formDataIsValid && (this.shippingAddressLine2Feedback !== this.valid)) {
                formDataIsValid = false;
            }
            if (formDataIsValid && (this.shippingCityFeedback !== this.valid)) {
                formDataIsValid = false;
            }
            if (formDataIsValid && (this.shippingStateFeedback !== this.valid)) {
                formDataIsValid = false;
            }
            if (formDataIsValid && (this.shippingPostcodeFeedback !== this.valid)) {
                formDataIsValid = false;
            }
            if (formDataIsValid && !billingSameAsShippingAddress) {
                if (formDataIsValid && (this.billingAddressLine1Feedback !== this.valid)) {
                    formDataIsValid = false;
                }
                if (formDataIsValid && (this.billingAddressLine2Feedback !== this.valid)) {
                    formDataIsValid = false;
                }
                if (formDataIsValid && (this.billingCityFeedback !== this.valid)) {
                    formDataIsValid = false;
                }
                if (formDataIsValid && (this.billingStateFeedback !== this.valid)) {
                    formDataIsValid = false;
                }
                if (formDataIsValid && (this.billingPostcodeFeedback !== this.valid)) {
                    formDataIsValid = false;
                }
            }

            return formIsValid ? '' : "disabled='disabled'";
        }
    },
	watch: {
		billingSameAsShippingAddress: 'clearBillingAddress'
	},
    methods: {
		clearBillingAddress: function(){
			this.billingAddressLine1 = '';
			this.billingAddressLine2 = '';
			this.billingPostcode = '';
			this.billingCity = '';
			this.billingState = '';
		},
        showEmailFeedback: function () {
            if (this.email.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/))
            {
                this.emailFeedback = this.valid;
            } else {
                this.emailFeedback = this.invalid;
            }
            ;
        },
        showFirstnameFeedback: function () {
            if (this.firstname.trim().match(/^[a-zA-Z]{2,20}$/))
            {
                this.firstnameFeedback = this.valid;
            } else {
                this.firstnameFeedback = this.invalid;
            }
            ;
        },
        showLastnameFeedback: function () {
            if (this.lastname.trim().match(/^[a-zA-Z]{2,20}$/))
            {
                this.lastnameFeedback = this.valid;
            } else {
                this.lastnameFeedback = this.invalid;
            }
            ;
        },
        showPassword1Feedback: function () {
            if (this.password1.match(/^[a-zA-Z0-9]{6,40}$/))
            {
                this.password1Feedback = this.valid;
            } else {
                this.password1Feedback = this.invalid;
            }
            ;
        },
        showPassword2Feedback: function () {
            if (this.password2 === this.password1)
            {
                this.password2Feedback = this.valid;
            } else {
                this.password2Feedback = this.invalid;
            }
            ;
        },
        showShippingAddressLine1Feedback: function () {
            if (this.shippingAddressLine1.trim().match(/^[0-9A-Za-z \-\/\']{4,40}$/))
            {
                this.shippingAddressLine1Feedback = this.valid;
            } else {
                this.shippingAddressLine1Feedback = this.invalid;
            }
            ;
        },
        showShippingAddressLine2Feedback: function () {
            if (this.shippingAddressLine2.trim().match(/^[0-9A-Za-z \-\/']{0,40}$/))
            {
                this.shippingAddressLine2Feedback = this.valid;
            } else {
                this.shippingAddressLine2Feedback = this.invalid;
            }
            ;
        },
        showShippingStateFeedback: function () {
            if (this.validStates.indexOf(this.shippingState.toLowerCase().trim()) >= 0)
            {
                this.shippingStateFeedback = this.valid;
            } else {
                this.shippingStateFeedback = this.invalid;
            }
            ;
        },
        showShippingPostcodeFeedback: function () {
            if (this.shippingPostcode.trim().match(/^[0-9]{4}$/))
            {
                this.shippingPostcodeFeedback = this.valid;
            } else {
                this.shippingPostcodeFeedback = this.invalid;
            }
            ;
        },
        showShippingCityFeedback: function () {
            if (this.shippingCity.trim().match(/^[a-zA-Z \-']{3,40}$/))
            {
                this.shippingCityFeedback = this.valid;
            } else {
                this.shippingCityFeedback = this.invalid;
            }
            ;
        },
        showBillingAddressLine1Feedback: function () {
            if (this.billingAddressLine1.trim().match(/^[0-9A-Za-z \-\/\']{4,40}$/))
            {
                this.billingAddressLine1Feedback = this.valid;
            } else {
                this.billingAddressLine1Feedback = this.invalid;
            }
            ;
        },
        showBillingAddressLine2Feedback: function () {
            if (this.billingAddressLine2.trim().match(/^[0-9A-Za-z \-\/']{0,40}$/))
            {
                this.billingAddressLine2Feedback = this.valid;
            } else {
                this.billingAddressLine2Feedback = this.invalid;
            }
            ;
        },
        showBillingStateFeedback: function () {
            if (this.validStates.indexOf(this.billingState.toLowerCase().trim()) >= 0)
            {
                this.billingStateFeedback = this.valid;
            } else {
                this.billingStateFeedback = this.invalid;
            }
            ;
        },
        showBillingPostcodeFeedback: function () {
            if (this.billingPostcode.trim().match(/^[0-9]{4}$/))
            {
                this.billingPostcodeFeedback = this.valid;
            } else {
                this.billingPostcodeFeedback = this.invalid;
            }
            ;
        },
        showBillingCityFeedback: function () {
            if (this.billingCity.trim().match(/^[a-zA-Z \-']{3,40}$/))
            {
                this.billingCityFeedback = this.valid;
            } else {
                this.billingCityFeedback = this.invalid;
            }
            ;
        }
    }
});
