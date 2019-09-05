using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace File.Picker.RNFilePicker
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNFilePickerModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNFilePickerModule"/>.
        /// </summary>
        internal RNFilePickerModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNFilePicker";
            }
        }
    }
}
