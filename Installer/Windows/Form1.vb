Imports System.IO

Public Class Form1

    Private Sub Label1_Click(sender As Object, e As EventArgs) Handles Label1.Click

    End Sub

    Private Sub LinkLabel1_LinkClicked(sender As Object, e As LinkLabelLinkClickedEventArgs) Handles LinkLabel1.LinkClicked
        Dim githubLink As String = "https://github.com/brendanmanning/jLinux"
        Process.Start(githubLink)
        ''If the user clicks the link, open the github source page
    End Sub

    Private Sub LinkLabel2_LinkClicked(sender As Object, e As LinkLabelLinkClickedEventArgs) Handles LinkLabel2.LinkClicked
        Dim websiteLink As String = "https://www.brendanmanning.com/"
        Process.Start(websiteLink)
    End Sub

    Private Sub RichTextBox1_TextChanged(sender As Object, e As EventArgs) Handles output.TextChanged

    End Sub

    Private Sub Button2_Click(sender As Object, e As EventArgs) Handles Button2.Click
        MsgBox("Exiting Installer...If you did not start installation, nothing has been changed. You may re-open this installer at any time to install jLinux. If you did install jLinux, then you're all set!", , "Quitting jLinux...")
        ''now close the app
        Close()
    End Sub
    Public Function installAction()
        output.AppendText("Creating temporary download directory..." & vbNewLine)
        output.ScrollToCaret()
        output.AppendText("Will be removed after installation..." & vbNewLine)
        output.ScrollToCaret()
        Dim tempDirText As String = "C:\.jLinuxInstaller\"
        ''prepare to make a termporary folder to download files to
        If (Not System.IO.Directory.Exists(tempDirText)) Then
            System.IO.Directory.CreateDirectory(tempDirText)
        End If
        output.AppendText("Directory C:\.jLinuxInstaller\ created!" & vbNewLine)
        output.ScrollToCaret()

        output.AppendText("Downloading JAR file..." & vbNewLine)
        output.ScrollToCaret()
        My.Computer.Network.DownloadFile(
    "http://thecyberbay.com/cdn/jLinux/latest/latest.jar",
    "C:\.jLinuxInstaller\jLinux.jar", False, 500)


        output.AppendText("JAR file downloaded..." & vbNewLine)
        output.ScrollToCaret()
        ''download jLinux updater
        output.AppendText("Downloading Update Utility....")
        output.ScrollToCaret()
        My.Computer.Network.DownloadFile(
   "http://thecyberbay.com/cdn/jLinux/latest/jLinuxUpdater.exe",
   "C:\.jLinuxInstaller\jLinuxUpdater.exe", False, 500)
        output.AppendText("Download Update Utility...")
        output.ScrollToCaret()

        output.AppendText("Preparing to create install directory" & vbNewLine)
        output.ScrollToCaret()

        Dim installDir As String = "C:\Program Files\jLinux\"
        If (Not System.IO.Directory.Exists(installDir)) Then
            System.IO.Directory.CreateDirectory(installDir)
        End If

        ''now copy the files from the temp dir to the install dir
        My.Computer.FileSystem.CopyFile("C:\.jLinuxInstaller\jLinux.jar", "C:\Program Files\jLinux\jLinux.jar")
        output.AppendText("JAR file moved...")
        output.ScrollToCaret()
        My.Computer.FileSystem.CopyFile("C:\.jLinuxInstaller\jLinuxUpdater.exe", "C:\Program Files\jLinux\jLinuxUpdater.exe")
        output.AppendText("Updater Copied...")
        output.ScrollToCaret()

        ''clean up and delete the temp folder
        My.Computer.FileSystem.DeleteFile("C:\.jLinuxInstaller\jLinux.jar")
        output.AppendText("Cleaning Up...")
        output.ScrollToCaret()
        My.Computer.FileSystem.DeleteFile("C:\.jLinuxInstaller\jLinuxUpdater.exe")
        My.Computer.FileSystem.DeleteDirectory("C:\.jLinuxInstaller\", FileIO.DeleteDirectoryOption.ThrowIfDirectoryNonEmpty)
        output.AppendText("Successfully Cleaned Up!")
        output.ScrollToCaret()


        Dim desktopPath As String = My.Computer.FileSystem.SpecialDirectories.Desktop & ""
        Dim batchPath As String = desktopPath & "\jLinuxStart.bat"

        If Not File.Exists(batchPath) Then
            ' Create the quick start batch
            Using sw As StreamWriter = File.CreateText(batchPath)
                sw.WriteLine("java -jar ""C:\Program Files\jLinux\jLinux.jar""")
            End Using
        End If


        output.AppendText("Created Quick Start File (" & batchPath & ")")
        output.ScrollToCaret()
        output.AppendText("Installation Successful!")
        output.ScrollToCaret()
        MsgBox("jLinux has finished installing! jLinux Installer will now quit", , "Installation Successful!!")
        Close()

        Return True
    End Function
    Private Sub installButton_Click(sender As Object, e As EventArgs) Handles installButton.Click
        output.AppendText("Starting Installation..." & vbNewLine)
        output.ScrollToCaret()
        installAction()
    End Sub
End Class
