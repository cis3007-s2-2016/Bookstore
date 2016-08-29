var gulp = require('gulp');
var sass = require('gulp-sass');
var rename = require('gulp-rename');
var sourcemaps = require('gulp-sourcemaps');


gulp.task('sass', function() {
    return gulp.src('src/main/webapp/WEB-INF/sass/main.scss')
    	.pipe(sourcemaps.init())
        .pipe(sass({includePaths:['node_modules/bootstrap-sass/assets/stylesheets/']}))
        .pipe(rename('styles.css'))
        .pipe(sourcemaps.write())
        .pipe(gulp.dest('src/main/webapp/resources/stylesheets/'));
});