import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.Node
import javafx.scene.control.TextArea
import javafx.stage.FileChooser
import javafx.stage.Stage
import java.net.URL
import java.util.*

class AppAdderController : Initializable {

    @FXML
    private lateinit var appTextArea: TextArea

    lateinit var installer: Installer

    override fun initialize(location: URL?, resources: ResourceBundle?) {
    }

    @FXML
    private fun loadButtonPressed(event: ActionEvent) {
        val fc = FileChooser()
        val fileExtensions = FileChooser.ExtensionFilter("Text File", "*")
        fc.extensionFilters.add(fileExtensions)
        fc.title = "Select an image"
        val file = fc.showOpenDialog((event.source as Node).scene.window)
        appTextArea.text = file?.readText()
    }

    @FXML
    private fun okButtonPressed(event: ActionEvent) {
        if (appTextArea.text != null && appTextArea.text.trim().isNotEmpty()) {
            for (line in appTextArea.text.trim().lines())
                installer.addApp(line)
            installer.createTables()
        }
        ((event.source as Node).scene.window as Stage).close()
    }
}