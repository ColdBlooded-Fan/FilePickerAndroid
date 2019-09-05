
# react-native-files-picker-android

## Getting started

`$ npm install react-native-files-picker-android --save`

### Mostly automatic installation

`$ react-native link react-native-files-picker-android`



#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNFilePickerPackage;` to the imports at the top of the file
  - Add `new RNFilePickerPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-files-picker-android'
  	project(':react-native-files-picker-android').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-files-picker-android/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-files-picker-android')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNFilePicker.sln` in `node_modules/react-native-files-picker-android/windows/RNFilePicker.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using File.Picker.RNFilePicker;` to the usings at the top of the file
  - Add `new RNFilePickerPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNFilePicker from 'react-native-files-picker-android';
RNFilePicker.openFileSelec((name, uriStr, fileType)=>{
console.log('fileName :' + name)
...
},()=>{
console.log('获取文件失败')
})


// TODO: What to do with the module?
RNFilePicker;
```
  