# Canela Emulator

This is my own testing version of cassiapp, I have no idea if I'll ever get the stuff to work. But if you want to use this anyway, just follow the instructions

## Getting the app

### Manually building it
* Just build the app using Android Studio lol

### Grabbing it from releases
* Or you can grab the APK at from this project's releases page

## Adding the goodies to Canela

### Manually building them
* Build the [cassiaext](https://github.com/MixedVictor/cassiaext), and the [runtime](https://github.com/MixedVictor/cassia-runtime) from my own forks using VSCode's Dev Containers.
* On cassiaext, grab the `prefix.tar.gz` from cassiaext inside the `build` folder
* And on the runtime, you'll have to zip the `prefix` folder that is inside `build` to `tar.gz`

### Grabbing the them from releases
* Or, you can also grab them from this project's releases page

After, that, just import them to Canela.

### What about the prefix?

For some reason, WINE doesn't want to create the damn prefix.
You can try to start your own prefix using a WINE from Termux like what I did, however, I haven't got anything to output to Termux:X11 (although I manually tested something like xclock and it worked)
