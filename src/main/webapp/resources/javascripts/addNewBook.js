new Vue({
    el: '#addBookApp',
    data: {
        isbn: '',
        title: '',
        authors: '',
        synopsis: '',
        category: '',
        format: '',
        publisher: '',
        publishedDate: '',
        thumbnailFiles: '',
        thumbnail: '',
        loadingIcon: false,
        imageFileReader: new FileReader(),
        localThumbnail: '',
        remoteThumbnail: ''
    },
    computed: {
        isbnValid: function(){
            return this.isbn.trim().match(/[0-9]{3}-?[0-9]{10}/g);
        }
    },
    methods: {
        autocompleteFromGoogleBooks: function (event) {
            var isbnNumbersOnly = this.isbn.replace('-','');
            var googleBooksApiURL = 'https://www.googleapis.com/books/v1/volumes?q=isbn:' + isbnNumbersOnly;

            this.loadingIcon = true;

            this.$http.get(googleBooksApiURL).then(function (response) {
                this.title = response.body.items[0].volumeInfo.title;
                var authors = response.body.items[0].volumeInfo.authors;

                for (var i=0;i < authors.length;i++){
                    this.authors += authors[i];
                    if (i < authors.length -1){
                        this.authors += ", ";
                    }
                }
                this.synopsis = response.body.items[0].volumeInfo.description;
                this.publisher = response.body.items[0].volumeInfo.publisher;
                this.publishedDate = response.body.items[0].volumeInfo.publishedDate;
                this.category = response.body.items[0].volumeInfo.categories[0];
                this.thumbnail = response.body.items[0].volumeInfo.imageLinks.thumbnail;
                this.remoteThumbnail = this.thumbnail;
                this.localThumbnail = "";
                this.loadingIcon = false;
            });
        },
        updateThumbnail: function(e){
            var files = e.target.files || e.dataTransfer.files;
            if (!files.length)
                return;
            var reader = new FileReader();
            var vm = this;
            reader.onload = (function(e){
                vm.thumbnail = e.target.result;
            });
            reader.readAsDataURL(files[0]);
        }
    }
});
