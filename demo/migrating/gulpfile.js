var gulp = require('gulp')
  , less = require("gulp-less");

// task
gulp.task('compile-less', function () {
  gulp.src('./Less/one.less') // path to your file
    .pipe(less())
    .pipe(gulp.dest('path/to/destination'));
});

gulp.task('default', [], function() {
  gulp.start('compile-less');
});