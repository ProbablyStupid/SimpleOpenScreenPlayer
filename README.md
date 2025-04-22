# Simple Open Screen Player

A simple Java Swing Application to display an image on full screen and can be made almost **impossible to close**. Light-weight and portable.
Java 8 compatible.

Found a way to close the application in `-noexit -captureMouse` mode? Great! Now open a ticket/issue so we can make the application even more robust. 😃

## Planned features

- [ ] Linux build scripts

## How it works

The application is a simple java swing window.
It will always try to get fullscreen and focus-priviliges.


## Usage

Use the flags at your own risk. This application is not intended for malicious use, simply for harmless and robust display purposes. The contributors of this software cannot be held liable for the effects of this software.

### There are the following flags:

- `-nofailsafe` disables the default exit-key, which is "A".
- `-noexit` will enable aggressive focus grabbing. Will use the keyboard. If the failsafe key is disabled, you will likely not be able to exit the application.
- `-captureMouse` will use the mouse to try even harder to get focus privileges.
- `-noexit_interval <integer>` adjust the time (in miliseconds) which the "noexit"-loop will wait before trying to grab focus again. A lower number will draw more performance. Values between 50 and 500 are recommended.

## Building

Either download the source code, replace sosp.png (ensure the new image is also called sosp.png), and compile (build.bat or build-java8.bat).

OR

Download the precompiled .jar files, rename them to .zip and open them as a zip file, and replace sosp.png with your image (ensure the new image is also called sosp.png).

## Compatibility

The application is fully Java 8 compatible. That means it will run on any computer with a JRE from java.com (most machines on the planet).
You can build this version with build-java8.bat.

If you want to build the newer java version, you will also need to build a custom runtime environment.
