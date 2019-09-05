
# react-native-file-picker

## Getting started

`$ npm install react-native-file-picker --save`

### Mostly automatic installation

`$ react-native link react-native-file-picker`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-file-picker` and add `RNFilePicker.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNFilePicker.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNFilePickerPackage;` to the imports at the top of the file
  - Add `new RNFilePickerPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-file-picker'
  	project(':react-native-file-picker').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-file-picker/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-file-picker')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNFilePicker.sln` in `node_modules/react-native-file-picker/windows/RNFilePicker.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using File.Picker.RNFilePicker;` to the usings at the top of the file
  - Add `new RNFilePickerPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNFilePicker from 'react-native-file-picker';

// TODO: What to do with the module?
RNFilePicker;
```
  