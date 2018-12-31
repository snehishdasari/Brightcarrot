package com.example.ravipavansne.brightcarrot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {


    private Spinner day ;
    private Spinner month ;
    private Spinner year ;
    private Spinner state ;
    private Spinner city ;
    private int stateindex;
    private JSONObject root;
    private JSONArray citieslist;
    private ArrayList<String> states;
    private ArrayList<String> cities;
    private userdetails curruserdetails;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private TextInputLayout firstname ;
    private TextInputLayout lastname ;
    private TextInputLayout phonenumber ;
    private TextInputLayout address ;
    private FirebaseUser firebaseUser ;
    private ProgressDialog progressDialog ;
    public static Button butt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        day=(Spinner) findViewById(R.id.spinnerday) ;
        month = (Spinner) findViewById(R.id.spinnermonth) ;
        year = (Spinner) findViewById(R.id.spinneryear) ;
        state = (Spinner) findViewById(R.id.spinnerstate) ;
        city = (Spinner) findViewById(R.id.spinnercity) ;

        //city.setEnabled(false);


        ArrayList<String> days = new ArrayList<>() ;
        for(int i=1;i<32;i++)
        {
            String k = String.valueOf(i) ;
            days.add(k) ;
        }

        ArrayList<String> months = new ArrayList<>() ;
        months.add("January") ;
        months.add("February") ;
        months.add("March") ;
        months.add("April") ;
        months.add("May") ;
        months.add("June") ;
        months.add("July") ;
        months.add("August") ;
        months.add("September") ;months.add("October") ;
        months.add("November") ;
        months.add("December") ;



        ArrayList<String> years = new ArrayList<>() ;
        for(int i=2000;i>1949;i--)
        {
            String k = String.valueOf(i) ;
            years.add(k) ;
        }


        ArrayAdapter<String> dayadapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,days) ;
        ArrayAdapter<String> monthadapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,months) ;
        ArrayAdapter<String> yearadapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,years) ;


        day.setAdapter(dayadapter);
        month.setAdapter(monthadapter);
        year.setAdapter(yearadapter) ;



        states =new ArrayList<>() ;
        cities = new ArrayList<>();
        cities.add("city");
        states.add("states");
        states.add("Andaman and Nicobar Islands") ;
        states.add("Andhra Pradesh") ;
        states.add("Arunachal Pradesh") ;
        states.add("Assam") ;
        states.add("Bihar") ;
        states.add("Chandigarh") ;
        states.add("Chhattisgarh") ;
        states.add("Dadra and Nagar Haveli") ;
        states.add("Delhi") ;
        states.add("Goa") ;
        states.add("Gujarat") ;
        states.add("Haryana") ;
        states.add("Himachal Pradesh") ;
        states.add("Jammu and Kashmir") ;
        states.add("Jharkhand") ;
        states.add("Karnataka") ;
        states.add("Karnatka") ;
        states.add("Kerala") ;
        states.add("Madhya Pradesh") ;
        states.add("Maharashtra") ;
        states.add("Manipur") ;
        states.add("Meghalaya") ;
        states.add("Mizoram") ;
        states.add("Nagaland") ;
        states.add("Odisha") ;
        states.add("Puducherry") ;
        states.add("Punjab") ;
        states.add("Rajasthan") ;
        states.add("Tamil Nadu") ;
        states.add("Telangana") ;
        states.add("Tripura") ;
        states.add("Uttar Pradesh") ;
        states.add("Uttarakhand") ;
        states.add("West Bengal") ;


        ArrayAdapter<String> stateadapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,states);
        state.setAdapter(stateadapter);
        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0)
                {
                    stateindex=i;
                    city.setEnabled(true);
                    String jsonfile = " {\"Andaman and Nicobar Islands\":[\"Port Blair*\"],\"Andhra Pradesh\":[\"Adoni\",\"Amalapuram\",\"Anakapalle\",\"Anantapur\",\"Bapatla\",\"Bheemunipatnam\",\"Bhimavaram\",\"Bobbili\",\"Chilakaluripet\",\"Chirala\",\"Chittoor\",\"Dharmavaram\",\"Eluru\",\"Gooty\",\"Gudivada\",\"Gudur\",\"Guntakal\",\"Guntur\",\"Hindupur\",\"Jaggaiahpet\",\"Jammalamadugu\",\"Kadapa\",\"Kadiri\",\"Kakinada\",\"Kandukur\",\"Kavali\",\"Kovvur\",\"Kurnool\",\"Macherla\",\"Machilipatnam\",\"Madanapalle\",\"Mandapeta\",\"Markapur\",\"Nagari\",\"Naidupet\",\"Nandyal\",\"Narasapuram\",\"Narasaraopet\",\"Narsipatnam\",\"Nellore\",\"Nidadavole\",\"Nuzvid\",\"Ongole\",\"Palacole\",\"Palasa Kasibugga\",\"Parvathipuram\",\"Pedana\",\"Peddapuram\",\"Pithapuram\",\"Ponnur\",\"Proddatur\",\"Punganur\",\"Puttur\",\"Rajahmundry\",\"Rajam\",\"Rajampet\",\"Ramachandrapuram\",\"Rayachoti\",\"Rayadurg\",\"Renigunta\",\"Repalle\",\"Salur\",\"Samalkot\",\"Sattenapalle\",\"Srikakulam\",\"Srikalahasti\",\"Srisailam Project (Right Flank Colony) Township\",\"Sullurpeta\",\"Tadepalligudem\",\"Tadpatri\",\"Tanuku\",\"Tenali\",\"Tirupati\",\"Tiruvuru\",\"Tuni\",\"Uravakonda\",\"Venkatagiri\",\"Vijayawada\",\"Vinukonda\",\"Visakhapatnam\",\"Vizianagaram\",\"Yemmiganur\",\"Yerraguntla\"],\"Arunachal Pradesh\":[\"Naharlagun\",\"Pasighat\"],\"Assam\":[\"Barpeta\",\"Bongaigaon City\",\"Dhubri\",\"Dibrugarh\",\"Diphu\",\"Goalpara\",\"Guwahati\",\"Jorhat\",\"Karimganj\",\"Lanka\",\"Lumding\",\"Mangaldoi\",\"Mankachar\",\"Margherita\",\"Mariani\",\"Marigaon\",\"Nagaon\",\"Nalbari\",\"North Lakhimpur\",\"Rangia\",\"Sibsagar\",\"Silapathar\",\"Silchar\",\"Tezpur\",\"Tinsukia\"],\"Bihar\":[\"Araria\",\"Arrah\",\"Arwal\",\"Asarganj\",\"Aurangabad\",\"Bagaha\",\"Barh\",\"Begusarai\",\"Bettiah\",\"Bhabua\",\"Bhagalpur\",\"Buxar\",\"Chhapra\",\"Darbhanga\",\"Dehri-on-Sone\",\"Dumraon\",\"Forbesganj\",\"Gaya\",\"Gopalganj\",\"Hajipur\",\"Jamalpur\",\"Jamui\",\"Jehanabad\",\"Katihar\",\"Kishanganj\",\"Lakhisarai\",\"Lalganj\",\"Madhepura\",\"Madhubani\",\"Maharajganj\",\"Mahnar Bazar\",\"Makhdumpur\",\"Maner\",\"Manihari\",\"Marhaura\",\"Masaurhi\",\"Mirganj\",\"Mokameh\",\"Motihari\",\"Motipur\",\"Munger\",\"Murliganj\",\"Muzaffarpur\",\"Narkatiaganj\",\"Naugachhia\",\"Nawada\",\"Nokha\",\"Patna*\",\"Piro\",\"Purnia\",\"Rafiganj\",\"Rajgir\",\"Ramnagar\",\"Raxaul Bazar\",\"Revelganj\",\"Rosera\",\"Saharsa\",\"Samastipur\",\"Sasaram\",\"Sheikhpura\",\"Sheohar\",\"Sherghati\",\"Silao\",\"Sitamarhi\",\"Siwan\",\"Sonepur\",\"Sugauli\",\"Sultanganj\",\"Supaul\",\"Warisaliganj\"],\"Chandigarh\":[\"Chandigarh*\"],\"Chhattisgarh\":[\"Ambikapur\",\"Bhatapara\",\"Bhilai Nagar\",\"Bilaspur\",\"Chirmiri\",\"Dalli-Rajhara\",\"Dhamtari\",\"Durg\",\"Jagdalpur\",\"Korba\",\"Mahasamund\",\"Manendragarh\",\"Mungeli\",\"Naila Janjgir\",\"Raigarh\",\"Raipur*\",\"Rajnandgaon\",\"Sakti\",\"Tilda Newra\"],\"Dadra and Nagar Haveli\":[\"Silvassa*\"],\"Delhi\":[\"Delhi\",\"New Delhi*\"],\"Goa\":[\"Mapusa\",\"Margao\",\"Marmagao\",\"Panaji*\"],\"Gujarat\":[\"Adalaj\",\"Ahmedabad\",\"Amreli\",\"Anand\",\"Anjar\",\"Ankleshwar\",\"Bharuch\",\"Bhavnagar\",\"Bhuj\",\"Chhapra\",\"Deesa\",\"Dhoraji\",\"Godhra\",\"Jamnagar\",\"Kadi\",\"Kapadvanj\",\"Keshod\",\"Khambhat\",\"Lathi\",\"Limbdi\",\"Lunawada\",\"Mahesana\",\"Mahuva\",\"Manavadar\",\"Mandvi\",\"Mangrol\",\"Mansa\",\"Mahemdabad\",\"Modasa\",\"Morvi\",\"Nadiad\",\"Navsari\",\"Padra\",\"Palanpur\",\"Palitana\",\"Pardi\",\"Patan\",\"Petlad\",\"Porbandar\",\"Radhanpur\",\"Rajkot\",\"Rajpipla\",\"Rajula\",\"Ranavav\",\"Rapar\",\"Salaya\",\"Sanand\",\"Savarkundla\",\"Sidhpur\",\"Sihor\",\"Songadh\",\"Surat\",\"Talaja\",\"Thangadh\",\"Tharad\",\"Umbergaon\",\"Umreth\",\"Una\",\"Unjha\",\"Upleta\",\"Vadnagar\",\"Vadodara\",\"Valsad\",\"Vapi\",\"Vapi\",\"Veraval\",\"Vijapur\",\"Viramgam\",\"Visnagar\",\"Vyara\",\"Wadhwan\",\"Wankaner\"],\"Haryana\":[\"Bahadurgarh\",\"Bhiwani\",\"Charkhi Dadri\",\"Faridabad\",\"Fatehabad\",\"Gohana\",\"Gurgaon\",\"Hansi\",\"Hisar\",\"Jind\",\"Kaithal\",\"Karnal\",\"Ladwa\",\"Mahendragarh\",\"Mandi Dabwali\",\"Narnaul\",\"Narwana\",\"Palwal\",\"Panchkula\",\"Panipat\",\"Pehowa\",\"Pinjore\",\"Rania\",\"Ratia\",\"Rewari\",\"Rohtak\",\"Safidon\",\"Samalkha\",\"Sarsod\",\"Shahbad\",\"Sirsa\",\"Sohna\",\"Sonipat\",\"Taraori\",\"Thanesar\",\"Tohana\",\"Yamunanagar\"],\"Himachal Pradesh\":[\"Mandi\",\"Nahan\",\"Palampur\",\"Shimla*\",\"Solan\",\"Sundarnagar\"],\"Jammu and Kashmir\":[\"Anantnag\",\"Baramula\",\"Jammu\",\"Kathua\",\"Punch\",\"Rajauri\",\"Sopore\",\"Srinagar*\",\"Udhampur\"],\"Jharkhand\":[\"Adityapur\",\"Bokaro Steel City\",\"Chaibasa\",\"Chatra\",\"Chirkunda\",\"Medininagar (Daltonganj)\",\"Deoghar\",\"Dhanbad\",\"Dumka\",\"Giridih\",\"Gumia\",\"Hazaribag\",\"Jamshedpur\",\"Jhumri Tilaiya\",\"Lohardaga\",\"Madhupur\",\"Mihijam\",\"Musabani\",\"Pakaur\",\"Patratu\",\"Phusro\",\"Ramgarh\",\"Ranchi*\",\"Sahibganj\",\"Saunda\",\"Simdega\",\"Tenu dam-cum-Kathhara\"],\"Karnataka\":[\"Adyar\",\"Afzalpur\",\"Arsikere\",\"Athni\",\"Bengaluru\",\"Belagavi\",\"Ballari\",\"Chikkamagaluru\",\"Davanagere\",\"Gokak\",\"Hubli-Dharwad\",\"Karwar\",\"Kolar\",\"Lakshmeshwar\",\"Lingsugur\",\"Maddur\",\"Madhugiri\",\"Madikeri\",\"Magadi\",\"Mahalingapura\",\"Malavalli\",\"Malur\",\"Mandya\",\"Mangaluru\",\"Manvi\",\"Mudalagi\",\"Mudabidri\",\"Muddebihal\",\"Mudhol\",\"Mulbagal\",\"Mundargi\",\"Nanjangud\",\"Nargund\",\"Navalgund\",\"Nelamangala\",\"Pavagada\",\"Piriyapatna\",\"Puttur\",\"Rabkavi Banhatti\",\"Raayachuru\",\"Ranebennuru\",\"Ramanagaram\",\"Ramdurg\",\"Ranibennur\",\"Robertson Pet\",\"Ron\",\"Sadalagi\",\"Sagara\",\"Sakaleshapura\",\"Sindagi\",\"Sanduru\",\"Sankeshwara\",\"Saundatti-Yellamma\",\"Savanur\",\"Sedam\",\"Shahabad\",\"Shahpur\",\"Shiggaon\",\"Shikaripur\",\"Shivamogga\",\"Surapura\",\"Shrirangapattana\",\"Sidlaghatta\",\"Sindhagi\",\"Sindhnur\",\"Sira\",\"Sirsi\",\"Siruguppa\",\"Srinivaspur\",\"Tarikere\",\"Tekkalakote\",\"Terdal\",\"Talikota\",\"Tiptur\",\"Tumkur\",\"Udupi\",\"Vijayapura\",\"Wadi\",\"Yadgir\"],\"Karnatka\":[\"Mysore\"],\"Kerala\":[\"Adoor\",\"Alappuzha\",\"Attingal\",\"Chalakudy\",\"Changanassery\",\"Cherthala\",\"Chittur-Thathamangalam\",\"Guruvayoor\",\"Kanhangad\",\"Kannur\",\"Kasaragod\",\"Kayamkulam\",\"Kochi\",\"Kodungallur\",\"Kollam\",\"Kottayam\",\"Kozhikode\",\"Kunnamkulam\",\"Malappuram\",\"Mattannur\",\"Mavelikkara\",\"Mavoor\",\"Muvattupuzha\",\"Nedumangad\",\"Neyyattinkara\",\"Nilambur\",\"Ottappalam\",\"Palai\",\"Palakkad\",\"Panamattom\",\"Panniyannur\",\"Pappinisseri\",\"Paravoor\",\"Pathanamthitta\",\"Peringathur\",\"Perinthalmanna\",\"Perumbavoor\",\"Ponnani\",\"Punalur\",\"Puthuppally\",\"Koyilandy\",\"Shoranur\",\"Taliparamba\",\"Thiruvalla\",\"Thiruvananthapuram\",\"Thodupuzha\",\"Thrissur\",\"Tirur\",\"Vaikom\",\"Varkala\",\"Vatakara\"],\"Madhya Pradesh\":[\"Alirajpur\",\"Ashok Nagar\",\"Balaghat\",\"Bhopal\",\"Ganjbasoda\",\"Gwalior\",\"Indore\",\"Itarsi\",\"Jabalpur\",\"Lahar\",\"Maharajpur\",\"Mahidpur\",\"Maihar\",\"Malaj Khand\",\"Manasa\",\"Manawar\",\"Mandideep\",\"Mandla\",\"Mandsaur\",\"Mauganj\",\"Mhow Cantonment\",\"Mhowgaon\",\"Morena\",\"Multai\",\"Mundi\",\"Murwara (Katni)\",\"Nagda\",\"Nainpur\",\"Narsinghgarh\",\"Narsinghgarh\",\"Neemuch\",\"Nepanagar\",\"Niwari\",\"Nowgong\",\"Nowrozabad (Khodargama)\",\"Pachore\",\"Pali\",\"Panagar\",\"Pandhurna\",\"Panna\",\"Pasan\",\"Pipariya\",\"Pithampur\",\"Porsa\",\"Prithvipur\",\"Raghogarh-Vijaypur\",\"Rahatgarh\",\"Raisen\",\"Rajgarh\",\"Ratlam\",\"Rau\",\"Rehli\",\"Rewa\",\"Sabalgarh\",\"Sagar\",\"Sanawad\",\"Sarangpur\",\"Sarni\",\"Satna\",\"Sausar\",\"Sehore\",\"Sendhwa\",\"Seoni\",\"Seoni-Malwa\",\"Shahdol\",\"Shajapur\",\"Shamgarh\",\"Sheopur\",\"Shivpuri\",\"Shujalpur\",\"Sidhi\",\"Sihora\",\"Singrauli\",\"Sironj\",\"Sohagpur\",\"Tarana\",\"Tikamgarh\",\"Ujjain\",\"Umaria\",\"Vidisha\",\"Vijaypur\",\"Wara Seoni\"],\"Maharashtra\":[\"[[]]\",\"Ahmednagar\",\"Akola\",\"Akot\",\"Amalner\",\"Ambejogai\",\"Amravati\",\"Anjangaon\",\"Arvi\",\"Aurangabad\",\"Bhiwandi\",\"Dhule\",\"Kalyan-Dombivali\",\"Ichalkaranji\",\"Kalyan-Dombivali\",\"Karjat\",\"Latur\",\"Loha\",\"Lonar\",\"Lonavla\",\"Mahad\",\"Malegaon\",\"Malkapur\",\"Mangalvedhe\",\"Mangrulpir\",\"Manjlegaon\",\"Manmad\",\"Manwath\",\"Mehkar\",\"Mhaswad\",\"Mira-Bhayandar\",\"Morshi\",\"Mukhed\",\"Mul\",\"Greater Mumbai*\",\"Murtijapur\",\"Nagpur\",\"Nanded-Waghala\",\"Nandgaon\",\"Nandura\",\"Nandurbar\",\"Narkhed\",\"Nashik\",\"Navi Mumbai\",\"Nawapur\",\"Nilanga\",\"Osmanabad\",\"Ozar\",\"Pachora\",\"Paithan\",\"Palghar\",\"Pandharkaoda\",\"Pandharpur\",\"Panvel\",\"Parbhani\",\"Parli\",\"Partur\",\"Pathardi\",\"Pathri\",\"Patur\",\"Pauni\",\"Pen\",\"Phaltan\",\"Pulgaon\",\"Pune\",\"Purna\",\"Pusad\",\"Rahuri\",\"Rajura\",\"Ramtek\",\"Ratnagiri\",\"Raver\",\"Risod\",\"Sailu\",\"Sangamner\",\"Sangli\",\"Sangole\",\"Sasvad\",\"Satana\",\"Satara\",\"Savner\",\"Sawantwadi\",\"Shahade\",\"Shegaon\",\"Shendurjana\",\"Shirdi\",\"Shirpur-Warwade\",\"Shirur\",\"Shrigonda\",\"Shrirampur\",\"Sillod\",\"Sinnar\",\"Solapur\",\"Soyagaon\",\"Talegaon Dabhade\",\"Talode\",\"Tasgaon\",\"Thane\",\"Tirora\",\"Tuljapur\",\"Tumsar\",\"Uchgaon\",\"Udgir\",\"Umarga\",\"Umarkhed\",\"Umred\",\"Uran\",\"Uran Islampur\",\"Vadgaon Kasba\",\"Vaijapur\",\"Vasai-Virar\",\"Vita\",\"Wadgaon Road\",\"Wai\",\"Wani\",\"Wardha\",\"Warora\",\"Warud\",\"Washim\",\"Yavatmal\",\"Yawal\",\"Yevla\"],\"Manipur\":[\"Imphal*\",\"Lilong\",\"Mayang Imphal\",\"Thoubal\"],\"Meghalaya\":[\"Nongstoin\",\"Shillong*\",\"Tura\"],\"Mizoram\":[\"Aizawl\",\"Lunglei\",\"Saiha\"],\"Nagaland\":[\"Dimapur\",\"Kohima*\",\"Mokokchung\",\"Tuensang\",\"Wokha\",\"Zunheboto\"],\"Odisha\":[\"Balangir\",\"Baleshwar Town\",\"Barbil\",\"Bargarh\",\"Baripada Town\",\"Bhadrak\",\"Bhawanipatna\",\"Bhubaneswar*\",\"Brahmapur\",\"Byasanagar\",\"Cuttack\",\"Dhenkanal\",\"Jatani\",\"Jharsuguda\",\"Kendrapara\",\"Kendujhar\",\"Malkangiri\",\"Nabarangapur\",\"Paradip\",\"Parlakhemundi\",\"Pattamundai\",\"Phulabani\",\"Puri\",\"Rairangpur\",\"Rajagangapur\",\"Raurkela\",\"Rayagada\",\"Sambalpur\",\"Soro\",\"Sunabeda\",\"Sundargarh\",\"Talcher\",\"Tarbha\",\"Titlagarh\"],\"Puducherry\":[\"Karaikal\",\"Mahe\",\"Pondicherry*\",\"Yanam\"],\"Punjab\":[\"Amritsar\",\"Barnala\",\"Batala\",\"Bathinda\",\"Dhuri\",\"Faridkot\",\"Fazilka\",\"Firozpur\",\"Firozpur Cantt.\",\"Gobindgarh\",\"Gurdaspur\",\"Hoshiarpur\",\"Jagraon\",\"Jalandhar Cantt.\",\"Jalandhar\",\"Kapurthala\",\"Khanna\",\"Kharar\",\"Kot Kapura\",\"Longowal\",\"Ludhiana\",\"Malerkotla\",\"Malout\",\"Mansa\",\"Moga\",\"Mohali\",\"Morinda, India\",\"Mukerian\",\"Muktsar\",\"Nabha\",\"Nakodar\",\"Nangal\",\"Nawanshahr\",\"Pathankot\",\"Patiala\",\"Pattran\",\"Patti\",\"Phagwara\",\"Phillaur\",\"Qadian\",\"Raikot\",\"Rajpura\",\"Rampura Phul\",\"Rupnagar\",\"Samana\",\"Sangrur\",\"Sirhind Fatehgarh Sahib\",\"Sujanpur\",\"Sunam\",\"Talwara\",\"Tarn Taran\",\"Urmar Tanda\",\"Zira\",\"Zirakpur\"],\"Rajasthan\":[\"Ajmer\",\"Alwar\",\"Bikaner\",\"Bharatpur\",\"Bhilwara\",\"Jaipur*\",\"Jodhpur\",\"Lachhmangarh\",\"Ladnu\",\"Lakheri\",\"Lalsot\",\"Losal\",\"Makrana\",\"Malpura\",\"Mandalgarh\",\"Mandawa\",\"Mangrol\",\"Merta City\",\"Mount Abu\",\"Nadbai\",\"Nagar\",\"Nagaur\",\"Nasirabad\",\"Nathdwara\",\"Neem-Ka-Thana\",\"Nimbahera\",\"Nohar\",\"Nokha\",\"Pali\",\"Phalodi\",\"Phulera\",\"Pilani\",\"Pilibanga\",\"Pindwara\",\"Pipar City\",\"Prantij\",\"Pratapgarh\",\"Raisinghnagar\",\"Rajakhera\",\"Rajaldesar\",\"Rajgarh (Alwar)\",\"Rajgarh (Churu)\",\"Rajsamand\",\"Ramganj Mandi\",\"Ramngarh\",\"Ratangarh\",\"Rawatbhata\",\"Rawatsar\",\"Reengus\",\"Sadri\",\"Sadulshahar\",\"Sadulpur\",\"Sagwara\",\"Sambhar\",\"Sanchore\",\"Sangaria\",\"Sardarshahar\",\"Sawai Madhopur\",\"Shahpura\",\"Shahpura\",\"Sheoganj\",\"Sikar\",\"Sirohi\",\"Sojat\",\"Sri Madhopur\",\"Sujangarh\",\"Sumerpur\",\"Suratgarh\",\"Taranagar\",\"Todabhim\",\"Todaraisingh\",\"Tonk\",\"Udaipur\",\"Udaipurwati\",\"Vijainagar, Ajmer\"],\"Tamil Nadu\":[\"Arakkonam\",\"Aruppukkottai\",\"Chennai*\",\"Coimbatore\",\"Erode\",\"Gobichettipalayam\",\"Kancheepuram\",\"Karur\",\"Lalgudi\",\"Madurai\",\"Manachanallur\",\"Nagapattinam\",\"Nagercoil\",\"Namagiripettai\",\"Namakkal\",\"Nandivaram-Guduvancheri\",\"Nanjikottai\",\"Natham\",\"Nellikuppam\",\"Neyveli (TS)\",\"O' Valley\",\"Oddanchatram\",\"P.N.Patti\",\"Pacode\",\"Padmanabhapuram\",\"Palani\",\"Palladam\",\"Pallapatti\",\"Pallikonda\",\"Panagudi\",\"Panruti\",\"Paramakudi\",\"Parangipettai\",\"Pattukkottai\",\"Perambalur\",\"Peravurani\",\"Periyakulam\",\"Periyasemur\",\"Pernampattu\",\"Pollachi\",\"Polur\",\"Ponneri\",\"Pudukkottai\",\"Pudupattinam\",\"Puliyankudi\",\"Punjaipugalur\",\"Ranipet\",\"Rajapalayam\",\"Ramanathapuram\",\"Rameshwaram\",\"Rasipuram\",\"Salem\",\"Sankarankoil\",\"Sankari\",\"Sathyamangalam\",\"Sattur\",\"Shenkottai\",\"Sholavandan\",\"Sholingur\",\"Sirkali\",\"Sivaganga\",\"Sivagiri\",\"Sivakasi\",\"Srivilliputhur\",\"Surandai\",\"Suriyampalayam\",\"Tenkasi\",\"Thammampatti\",\"Thanjavur\",\"Tharamangalam\",\"Tharangambadi\",\"Theni Allinagaram\",\"Thirumangalam\",\"Thirupuvanam\",\"Thiruthuraipoondi\",\"Thiruvallur\",\"Thiruvarur\",\"Thuraiyur\",\"Tindivanam\",\"Tiruchendur\",\"Tiruchengode\",\"Tiruchirappalli\",\"Tirukalukundram\",\"Tirukkoyilur\",\"Tirunelveli\",\"Tirupathur\",\"Tirupathur\",\"Tiruppur\",\"Tiruttani\",\"Tiruvannamalai\",\"Tiruvethipuram\",\"Tittakudi\",\"Udhagamandalam\",\"Udumalaipettai\",\"Unnamalaikadai\",\"Usilampatti\",\"Uthamapalayam\",\"Uthiramerur\",\"Vadakkuvalliyur\",\"Vadalur\",\"Vadipatti\",\"Valparai\",\"Vandavasi\",\"Vaniyambadi\",\"Vedaranyam\",\"Vellakoil\",\"Vellore\",\"Vikramasingapuram\",\"Viluppuram\",\"Virudhachalam\",\"Virudhunagar\",\"Viswanatham\"],\"Telangana\":[\"Adilabad\",\"Bellampalle\",\"Bhadrachalam\",\"Bhainsa\",\"Bhongir\",\"Bodhan\",\"Farooqnagar\",\"Gadwal\",\"Hyderabad*\",\"Jagtial\",\"Jangaon\",\"Kagaznagar\",\"Kamareddy\",\"Karimnagar\",\"Khammam\",\"Koratla\",\"Kothagudem\",\"Kyathampalle\",\"Mahbubnagar\",\"Mancherial\",\"Mandamarri\",\"Manuguru\",\"Medak\",\"Miryalaguda\",\"Nagarkurnool\",\"Narayanpet\",\"Nirmal\",\"Nizamabad\",\"Palwancha\",\"Ramagundam\",\"Sadasivpet\",\"Sangareddy\",\"Siddipet\",\"Sircilla\",\"Suryapet\",\"Tandur\",\"Vikarabad\",\"Wanaparthy\",\"Warangal\",\"Yellandu\"],\"Tripura\":[\"Agartala*\",\"Belonia\",\"Dharmanagar\",\"Kailasahar\",\"Khowai\",\"Pratapgarh\",\"Udaipur\"],\"Uttar Pradesh\":[\"Achhnera\",\"Agra\",\"Aligarh\",\"Allahabad\",\"Amroha\",\"Azamgarh\",\"Bahraich\",\"Chandausi\",\"Etawah\",\"Firozabad\",\"Fatehpur Sikri\",\"Hapur\",\"Hardoi *\",\"Jhansi\",\"Kalpi\",\"Kanpur\",\"Khair\",\"Laharpur\",\"Lakhimpur\",\"Lal Gopalganj Nindaura\",\"Lalitpur\",\"Lalganj\",\"Lar\",\"Loni\",\"Lucknow*\",\"Mathura\",\"Meerut\",\"Modinagar\",\"Moradabad\",\"Nagina\",\"Najibabad\",\"Nakur\",\"Nanpara\",\"Naraura\",\"Naugawan Sadat\",\"Nautanwa\",\"Nawabganj\",\"Nehtaur\",\"Niwai\",\"Noida\",\"Noorpur\",\"Obra\",\"Orai\",\"Padrauna\",\"Palia Kalan\",\"Parasi\",\"Phulpur\",\"Pihani\",\"Pilibhit\",\"Pilkhuwa\",\"Powayan\",\"Pukhrayan\",\"Puranpur\",\"Purquazi\",\"Purwa\",\"Rae Bareli\",\"Rampur\",\"Rampur Maniharan\",\"Rampur Maniharan\",\"Rasra\",\"Rath\",\"Renukoot\",\"Reoti\",\"Robertsganj\",\"Rudauli\",\"Rudrapur\",\"Sadabad\",\"Safipur\",\"Saharanpur\",\"Sahaspur\",\"Sahaswan\",\"Sahawar\",\"Sahjanwa\",\"Saidpur\",\"Sambhal\",\"Samdhan\",\"Samthar\",\"Sandi\",\"Sandila\",\"Sardhana\",\"Seohara\",\"Shahabad, Hardoi\",\"Shahabad, Rampur\",\"Shahganj\",\"Shahjahanpur\",\"Shamli\",\"Shamsabad, Agra\",\"Shamsabad, Farrukhabad\",\"Sherkot\",\"Shikarpur, Bulandshahr\",\"Shikohabad\",\"Shishgarh\",\"Siana\",\"Sikanderpur\",\"Sikandra Rao\",\"Sikandrabad\",\"Sirsaganj\",\"Sirsi\",\"Sitapur\",\"Soron\",\"Suar\",\"Sultanpur\",\"Sumerpur\",\"Tanda\",\"Thakurdwara\",\"Thana Bhawan\",\"Tilhar\",\"Tirwaganj\",\"Tulsipur\",\"Tundla\",\"Ujhani\",\"Unnao\",\"Utraula\",\"Varanasi\",\"Vrindavan\",\"Warhapur\",\"Zaidpur\",\"Zamania\"],\"Uttarakhand\":[\"Bageshwar\",\"Dehradun\",\"Haldwani-cum-Kathgodam\",\"Hardwar\",\"Kashipur\",\"Manglaur\",\"Mussoorie\",\"Nagla\",\"Nainital\",\"Pauri\",\"Pithoragarh\",\"Ramnagar\",\"Rishikesh\",\"Roorkee\",\"Rudrapur\",\"Sitarganj\",\"Srinagar\",\"Tehri\"],\"West Bengal\":[\"Adra\",\"Alipurduar\",\"Arambagh\",\"Asansol\",\"Baharampur\",\"Balurghat\",\"Bankura\",\"Darjiling\",\"English Bazar\",\"Gangarampur\",\"Habra\",\"Hugli-Chinsurah\",\"Jalpaiguri\",\"Jhargram\",\"Kalimpong\",\"Kharagpur\",\"Kolkata\",\"Mainaguri\",\"Malda\",\"Mathabhanga\",\"Medinipur\",\"Memari\",\"Monoharpur\",\"Murshidabad\",\"Nabadwip\",\"Naihati\",\"Panchla\",\"Pandua\",\"Paschim Punropara\",\"Purulia\",\"Raghunathpur\",\"Raghunathganj\",\"Raiganj\",\"Rampurhat\",\"Ranaghat\",\"Sainthia\",\"Santipur\",\"Siliguri\",\"Sonamukhi\",\"Srirampore\",\"Suri\",\"Taki\",\"Tamluk\",\"Tarakeswar\"]}";
                    try {
                        root = new JSONObject(jsonfile);
                        citieslist = root.getJSONArray(states.get(stateindex));
                        cities.clear();
                        cities.add("city");
                        for(int j=0;i<citieslist.length();j++)
                        {
                            cities.add(citieslist.getString(j));
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                else
                {
                    city.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        ArrayAdapter<String> citiesadapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,cities) ;
        city.setAdapter(citiesadapter);


        firstname = (TextInputLayout) findViewById(R.id.firstnameid) ;
        lastname = (TextInputLayout) findViewById(R.id.lastnameid) ;
        phonenumber=(TextInputLayout) findViewById(R.id.phonenumberid) ;
        address=(TextInputLayout) findViewById(R.id.addressid) ;


        firebaseDatabase = FirebaseDatabase.getInstance();


        progressDialog  = new ProgressDialog(this) ;



         butt = (Button)findViewById(R.id.next);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fname = firstname.getEditText().getText().toString();
                String lname = lastname.getEditText().getText().toString();
                String mobileno = phonenumber.getEditText().getText().toString();
                String addrs = address.getEditText().getText().toString();
                String dayofbirth = day.getSelectedItem().toString();
                String monthofbirth = month.getSelectedItem().toString();
                String yearofbirth = year.getSelectedItem().toString();
                String state1 = state.getSelectedItem().toString();
                String city1 = city.getSelectedItem().toString();
                boolean flag = false;
                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                String email = firebaseUser.getEmail();
                if (!TextUtils.isEmpty(fname) || !TextUtils.isEmpty(lname) || !TextUtils.isEmpty(mobileno) || !TextUtils.isEmpty(addrs) || !TextUtils.isEmpty(dayofbirth) || !TextUtils.isEmpty(monthofbirth) || !TextUtils.isEmpty(yearofbirth) || !TextUtils.isEmpty(state1) || !TextUtils.isEmpty(city1)) {


                    progressDialog.setTitle("Registering User");
                    progressDialog.setMessage("Please wait while we create your account");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();




                    userdetails u1 = new userdetails(fname, lname, addrs, mobileno, dayofbirth, monthofbirth, yearofbirth, state1, city1, email, flag);
                    u1.setFlag(true);
                    String id = firebaseUser.getUid();
                    databaseReference = firebaseDatabase.getReference().child("Users").child(id);
                    databaseReference.setValue(u1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                                progressDialog.dismiss();

                                Intent k = new Intent(getApplicationContext(), Home2Activity.class) ;
                                startActivity(k);
                                k.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK) ;
                                finish();

                            }

                            else
                            {
                                progressDialog.hide();
                                Toast.makeText(SignupActivity.this,"YOu have some error",Toast.LENGTH_LONG).show();
                            }
                        }
                    });



                }
            }
        });








    }
}
