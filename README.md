### Usage
```
git clone https://github.com/barteo/martian-robots.git
cd martian-robots
./gradlew test -i
```
Test using sample data is implemented in `src/test/kotlin/com/redbadger/robots/End2EndTest.kt`

### TODO
- write unit tests for MarsSurfaceController
- add runtime checks for:
    - the maximum value for any coordinate is 50
    - all instruction strings will be less than 100 characters in length
- document code classes and methods
- introduce message parsing library which can better handle format checking
