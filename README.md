# Modellipse 1.0 M5 Source Set

To make a Modellipse, we need Papyrus Sources and various dependent packages.

To setup a Modellipse Initial Source Set,

1. Download the sources
2. Unpack pack.gz's
3. Include Papyrus Plugins into Eclipse
4. Install dependent plugins
5. Remove unused sources
6. Resolve compile errors

## 1. Download the sources

Papyrus provides Git Clone URL, which is not working.

So we need to download the sources manually from the **Download** section of [http://git.eclipse.org/c/papyrus/org.eclipse.papyrus.git/](http://git.eclipse.org/c/papyrus/org.eclipse.papyrus.git/)

The 1.0 M5 release is 

>[http://git.eclipse.org/c/papyrus/org.eclipse.papyrus.git/snapshot/org.eclipse.papyrus-1.0.0_M5.zip](http://git.eclipse.org/c/papyrus/org.eclipse.papyrus.git/snapshot/org.eclipse.papyrus-1.0.0_M5.zip)

## 2. Unpack pack.gz's

Some plugins are provided as a pack.gz format. So we need to unpack all pack.gz files using unpack200 which is provided with JDK.

To make things simple, the script file is provided here.

**SCRIPT FILE LINK**

## 3. Include Papyrus Plugins into Eclipse

In Eclipse, File > Import... >

Select **Plug-ins and fragments**
![](http://i.imgur.com/iJGXSkm.png)

Set the directory where the downloaded and unpacked files are.

Select **Select from all plug-ins and fragments found at the specified location**

Select **Projects with source folders**

![](http://i.imgur.com/eTmOyIM.png)

Click **Add all**
![](http://i.imgur.com/hpUdX0Y.png)

We don't need **com.google.guava**, so exclude it.

![](http://i.imgur.com/Y3iDmsQ.png)


After import, over 15,000 errors will come up.

But don't be frustrated. Most of them will disappear after installing dependent packages.

## 4. Install dependent plugins

The external plugins are listed here.

>[http://wiki.eclipse.org/Papyrus_Required_External_Plugins](http://wiki.eclipse.org/Papyrus_Required_External_Plugins)

But for the practical workflow, follow the process below.

### Where to find plugins
All dependent plugins can be installed through the update site :  
>Luna - [http://download.eclipse.org/releases/luna](http://download.eclipse.org/releases/luna)

### How to install plugins

Choose Help > Install new software... and select the link above in **Work with**

![](http://i.imgur.com/TokcuWo.png)

### Dependent Plugins by keyword

Install plugins below using filtering with **Keywords**. The order might not be critical.

- **EMF**

    EMF Facet SDK, EMF Compare UML2, EMF Model Transaction SDK

- **xtext**

    xText SDK

- **zest**

    Zest Visualization Toolkit

- **GMF**

    GMF Tooling SDK

- **QVT**

    QVT Operational SDK

- **Modisco**

    Modisco

- **OCL**

    OCL Examples and Editors

- **wst**

    WST XML Core

    Search with the keyword `wst` and uncheck the **Group items category** and select **WST XML CORE**

## 5. Remove unused sources

- Files to remove
    **org.eclipse.papyrus.infra.tools.databinding.MultipleObservableList.java**
    - This file is not referenced by any resources and is safe to delete.
    - See this
        >[ http://www.eclipse.org/forums/index.php/t/351495/]( http://www.eclipse.org/forums/index.php/t/351495/)
        
- Packages to remove

    **org.eclipse.papyrus.uml.properties.xtext**

    - This package is not referenced by any resources and is safe to delete.
    
    - The files in this package imports `org.eclipse.papyrus.infra.widgets.xtext` which doesn't exist.
    

## 6. Resolve compile errors

This is really stupid and tedious. But there is no other way out.

At this time, About 40 errors remains and all of them are about **@Override**.

Manually delete **@Override** in the source  using **`Quick fix`**.

# Run Configurations

Eclipse Application Configuration for running with modified sources.

## Create Run Configuration

Open `plugin.xml` in any project and click **`Launch an Eclipse application`**

If an eclipse application launches, just close it after the launch completed. We will reconfigure it.

## Set VM Arguments

Run > Run Configurations...

Go to **`Arguments`** tab.

Paste the contents below into **`VM arguments`**

>-Dosgi.requiredJavaVersion=1.6 -Xms512m -Xmx512m -XX:PermSize=256m -XX:MaxPermSize=256m -XX:NewSize=256m -XX:MaxNewSize=256m

## Plugins Configuration

Go to **`Plug-ins`** tab.

Click **`Deselect All`**

Check all plugins in **`Workspace`**.

Uncheck all plugins in **`Taret Platform`**

Click **`Add Required Plug-ins`**

Click **`Validate Plug-ins`** and resolve unsatisfied problems.

>For Papyrus 1.0 M5 follow the process below.
>
>org.apache.batik.css : Check both 1.7.0, 1.6.0    
>org.apache.batik.util : Check both 1.7.0, 1.6.0
>org.apache.batik.util.gui : Check both 1.7.0, 1.6.0
>
>Check org.eclipse.ui.ide.workbench
>
>Click **`Add Required Plug-ins`**

# Make Git Repository

## .gitignore

Just add bin/ for now.

## Include emtpy directories

Empty directory is basically not added by `git add`. So we need to add meaningless file to make empty directories addible.

> find . -type d -empty -exec touch {}/.keep \;

## LF, CRLF Problems

`git add .` produces errors like below

    The file will have its original line endings in your working directory.
    warning: LF will be replaced by CRLF

Add below to the [core] of repository config file

> git config core.autocrlf false