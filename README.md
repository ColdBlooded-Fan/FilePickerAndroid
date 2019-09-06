
# react-native-files-picker-android-baozi

## Getting started

`$ npm react-native-files-picker-android-baozi --save`

### Mostly automatic installation

`$ react-native link react-native-files-picker-android-baozi`



#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNFilePickerPackage;` to the imports at the top of the file
  - Add `new RNFilePickerPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-files-picker-android-baozi'
  	project(':react-native-files-picker-android-baozi').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-files-picker-android-baozi/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-files-picker-android-baozi')
  	```




## Usage
```javascript
import RNFilePicker from 'react-native-files-picker-android-baozi';
RNFilePicker.openFileSelec((fileName, fileUri, fileType)=> {
// fileName(文件名) fileUri(文件uri) fileType(文件格式)
...
},()=>{
console.log('获取文件失败')
})


// TODO: What to do with the module?
RNFilePicker;
```
  