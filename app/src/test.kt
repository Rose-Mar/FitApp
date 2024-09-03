

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}











public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}


import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {
        delay(1000L)
        println("World!")
    }
    println("Hello,")
}



@interface ViewController : UIViewController
@property (nonatomic, strong) NSString *name;
@end

@implementation ViewController
- (void)viewDidLoad {
    [super viewDidLoad];
    self.name = @"Hello, World!";
    NSLog(@"%@", self.name);
}
@end


class ViewController: UIViewController {
    var name: String?

    override func viewDidLoad() {
        super.viewDidLoad()
        name = "Hello, World!"
        if let name = name {
            print(name)
        }
    }
}
