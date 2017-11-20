# ProtoGen
Eclipse IDE plugin to autocompile Google's Protocol Buffer files (.proto).

## Requirements

 - Eclipse Indigo or newer.

 - JavaSE-1.5 or newer.

## How to Install

### Install the Plugin to Eclipse

- Download the **protogen.zip** file from this repository.
- Extract the .zip file somewhere in your machine.
- Open Eclipse, and open the *Help → Install New Software...* menu.
- Add a new repository with name *"ProtoGen Repository"* and click the *Local* button and browse to the directory where you extracted the .zip file. (See last section of this README if you need more help).
- Make sure the *"Group items by category"* is unchecked.
- Check the ProtoGen feature and click next/finish/accept until you are asked to restart Eclipse.

### Add ProtoCompiler (Protoc) to PATH

- Download the proto compiler from: https://github.com/google/protobuf/releases 

#### Windows

- Open System (You can find this in Control Panel under the *System and Security* category), and select *Advanced system settings*. 
- In the *Advanced* tab select *"Environment Variables..."* and edit either your user "Path" variable or the system one (probably the former option).
- Add a new entry with the directory were you have extracted the Protocol Buffer compiler code. For example: *"C:\Program Files (x86)\ProtoBuffers\bin"* (without colons). The directory must end in "*bin*" and the *protoc.exe* should be in that folder. 

To check if working, press Windows key + R and type *cmd.* Write `protoc --version` and you should get your proto compiler version.

#### Linux

One way to do this is creating a soft link to the directory where you extracted the proto compiler. You can do this with the following command:

    sudo ln -s /path/to/bin/protoc /usr/bin/protoc

To make this work for the current session run:

    source ~/.profile

## How to Contribute

### Requirements

- Eclipse Luna or newer.

- JavaSE-1.7 or newer.

### Add Projects into Eclipse

- After you have cloned the projects, import the projects to Eclipse. To do this, open Eclipse and then select *File → Import → General (Existing Projects Into Workspace)*.
- Select root directory: *You should pick the cloned directory.*
- Finally, check both projects and select Finish.

### Install Eclipse PDE Plug-in Development Resources

- If the *plugin.xml* is not recognized (e.g.: It might be unable to resolve imports), you will need to download **Eclipse PDE Plug-in Development Resources**. To do this, you must install it from within the *"Install New Software..."* menu (There's a section that explains more at the end of this README). 

- Select work with: 
*&lt;EclipseVersion&gt;* - http://download.eclipse.org/releases/*&lt;EclipseVersion&gt;*

- Make sure *"Group items by category"* is unchecked.
- Pick **Eclipse PDE Plug-in Development Resources**.

### Testing Locally

#### Launching Eclipse from within Eclipse (Inception-style)

You can test locally by right-clicking the project and selecting *Run As →  Eclipse Application*. If that option doesn't show up, you can create a new *Run Configuration* for it.

#### Installing the Feature

You can try exporting the feature and installing it with the *"Install New Software...*" option in the following way:

- Open the *feature.xml* of the ProtoGenFeature project.
- On the *Overview tab*, on the left side under the *Exporting* category click *Export Wizard"*.
- Press the *Directory* button and browse/create a folder that will work as a .p2 repository for the feature.
- Press *Finish* button.
Finally install the feature through the *"Install New Software..."* menu.


### Install  

### How to Install New Software

You can find the *"Install New Software..."* option in the *Help* context menu.

In this window you can pick an already existing repository to work with by selecting one from the *"Work with"* select menu, or you can add one yourself by clicking add. When adding a repository you can add a local one by clicking the *Local* button, or a remote one by pasting the repository URI in the *Location* text box. You can write whatever you want on the name text box.

Once you selected a repository, you should make sure to uncheck the *"Group items by category"* option. Then you might need to wait until the plugin/features show up. Select the corresponding one (you can also use the search bar), and click *Next* twice. Then make sure to accept the license agreement, and press *Finish*.