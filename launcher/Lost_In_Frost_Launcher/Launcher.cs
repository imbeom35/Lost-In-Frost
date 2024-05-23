using System;
using System.IO;
using System.IO.Compression;
using System.Windows.Forms;
using System.Net.Http;
using System.Diagnostics;
using System.Drawing;
using MaterialSkin;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;

namespace LostInFrost
{
    public partial class Launcher : Form
    {
        public const int NOT = 0;
        public const int OLD = 1;
        public const int INS = 2;
        public const int NEW = 3;
        public string token = null;
        public int state = NOT;
        public readonly String extractPath = Environment.GetFolderPath(Environment.SpecialFolder.MyDocuments) + "\\Lost_In_Frost";
        public readonly MaterialSkinManager materialSkinManager;

        public Launcher(string tokenValue)
        {
            InitializeComponent();
            this.StartPosition = FormStartPosition.CenterScreen;
            this.Location = new Point((Screen.PrimaryScreen.WorkingArea.Width - this.Width) / 2, (Screen.PrimaryScreen.WorkingArea.Height - this.Height) / 2);
            this.FormBorderStyle = FormBorderStyle.FixedSingle;
            this.Text = "Lost In Frost Launcher";
            this.token = tokenValue;


            // MaterialSkinManager 초기화
            materialSkinManager = MaterialSkinManager.Instance;
            //materialSkinManager.AddFormToManage(this);
            materialSkinManager.Theme = MaterialSkinManager.Themes.LIGHT;
            materialSkinManager.ColorScheme = new ColorScheme(
                Primary.Yellow700, Primary.Yellow900,
                Primary.Yellow300, Accent.Yellow400,
                TextShade.WHITE
            );
            btn_install.BackColor = materialSkinManager.ColorScheme.PrimaryColor;
            btn_start.BackColor = materialSkinManager.ColorScheme.AccentColor;

            // MaterialLabel의 글자색 변경
            Label.ForeColor = Color.White;

            // 게임파일 버전확인
            Version();

            // 이벤트 등록
            btn_install.Click += Btn_Install_Click;
            btn_start.Click += Btn_Start_Click;
        }

        private async Task Version()
        {
            // 실행파일 확인
            FileInfo fi = new FileInfo(extractPath + "\\Lost_In_Frost.exe");

            // 실행파일이 존재할 경우
            if (fi.Exists)
            {
                // 최신버전 확인
                if (await VersionCheck())
                {
                    SetState(NEW);
                }
                else
                {
                    SetState(OLD);
                }
            }
            else
            {
                SetState(NOT);
            }
        }

        private async Task<bool> VersionCheck()
        {
            string filePath = extractPath + "\\version.txt";

            if (File.Exists(filePath))
            {
                // 해시 값 읽기
                SHA1 mySHA1 = SHA1.Create();
                //파일 읽기
                FileStream target = File.OpenRead(filePath);
                //다운받은 파일의 해시값 byte[]에 저장
                byte[] byteTarget = mySHA1.ComputeHash(target);
                //해시값 문자열로 변환
                string strTargetHash = GetStringFromHash(byteTarget);
                //파일 닫기
                target.Close();

                try
                {
                    using (HttpClient client = new HttpClient())
                    {
                        client.BaseAddress = new Uri("https://k9c101.p.ssafy.io");
                
                        // API 요청
                        HttpResponseMessage response = await client.GetAsync("/api/game/file/version/" + strTargetHash);
                
                        if (response.IsSuccessStatusCode)
                        {
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show("통신 실패: " + ex.Message);
                    return false;
                }

                return true;
            }
            else
            {
                return false;
            }
        }

        private static string GetStringFromHash(byte[] hash)
        {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < hash.Length; i++)
            {
                //byte[]를 16진수 문자열로
                result.Append(hash[i].ToString("X2"));
            }
            return result.ToString();
        }

        private void SetState(int value)
        {
            state = value;

            switch (value)
            {
                case NOT:
                    progressBar.Value = 0;
                    btn_install.Enabled = true;
                    btn_start.Enabled = false;
                    Label.Text = "설치가 필요합니다.";
                    break;
                case OLD:
                    progressBar.Value = 0;
                    btn_install.Enabled = true;
                    btn_start.Enabled = false;
                    Label.Text = "업데이트가 필요합니다.";
                    break;
                case INS:
                    btn_install.Enabled = false;
                    btn_start.Enabled = false;
                    Label.Text = "설치중입니다.(최대 30초가 소요됩니다)";
                    break;
                case NEW:
                    progressBar.Value = 100;
                    btn_install.Enabled = false;
                    btn_start.Enabled = true;
                    Label.Text = "최신버전입니다.";
                    break;
            }

        }

        private async void Btn_Install_Click(object sender, EventArgs e)
        {
            // 이전 상태 저장
            int temp = state;
            SetState(INS);
<<<<<<< HEAD
            Directory.Delete(extractPath, true);
=======
                Directory.Delete(extractPath, true);
                MessageBox.Show(extractPath);
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626

            // 해당 경로의 모든 파일을 가져와서 삭제
            try
            {
                string[] files = Directory.GetFiles(extractPath);
                foreach (string file in files)
                {
                    File.Delete(file);
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"An error occurred: {ex.Message}");
            }

            // 폴더가 존재하지 않을 경우 폴더 생성
            DirectoryInfo di = new DirectoryInfo(extractPath);
            if (di.Exists == false)
            {
                di.Create();
            }

            // 새로운 런처를 실행할 때
            if (Path.GetDirectoryName(Application.ExecutablePath) != extractPath)
            {
                // 기존 런처 제거
                try
                {
                    if (File.Exists(extractPath + "\\Lost_In_Frost_Launcher.exe"))
                    {
                        File.Delete(extractPath + "\\Lost_In_Frost_Launcher.exe");
                    }
                }
                catch (Exception ex)
                {
                    Console.WriteLine($"오류 발생: {ex.Message}");
                }

                // 런처 설치(복제)
                string currentExePath = System.Reflection.Assembly.GetEntryAssembly().Location;
                try
                {
                    File.Copy(currentExePath, Path.Combine(extractPath, "Lost_In_Frost_Launcher.exe"), true);
                }
                catch (Exception ex)
                {
                    MessageBox.Show("파일 복사 오류: " + ex.Message);
                }
            }

            // 압축파일 받아오기
            byte[] data = null;

            try
            {
                using (HttpClient client = new HttpClient())
                {
                    client.BaseAddress = new Uri("https://k9c101.p.ssafy.io");

                    // API 요청
                    HttpResponseMessage response = await client.GetAsync("/api/game/file/game-download");

                    if (response.IsSuccessStatusCode)
                    {
                        data = await response.Content.ReadAsByteArrayAsync();
                        progressBar.Value = 50;
                    }
                    else
                    {
                        state = temp;
                        MessageBox.Show("데이터를 가져올 수 없습니다 " + response.StatusCode);
                    }
                }
            }
            catch (Exception ex)
            {
                state = temp;
                MessageBox.Show("설치 실패: " + ex.Message);
            }

            if (data == null)
            {
                state = temp;
                MessageBox.Show("설치할 파일을 불러오지 못했습니다");
                return;
            }

            try
            {
                // string zipFilePath = extractPath + Guid.NewGuid().ToString() + ".zip";
                string zipFilePath = extractPath + "/" + Guid.NewGuid().ToString() + ".zip";
                Console.WriteLine(zipFilePath);

                // 임시 ZIP 파일 생성
                File.WriteAllBytes(zipFilePath, data);

                // 폴더 생성(이미 생성되어 있음)
                //Directory.CreateDirectory(extractPath);

                // ZIP 파일 추출
                ZipFile.ExtractToDirectory(zipFilePath, extractPath);

                // 임시 ZIP 파일 삭제
                File.Delete(zipFilePath);
            }
            catch (Exception ex)
            {
                state = temp;
                Console.WriteLine("게임 설치 중 오류 발생: " + ex.Message);
            }

            if(await VersionCheck())
            {
                SetState(NEW);
            }
            else
            {
                SetState(OLD);
            }
        }

        private async void Btn_Start_Click(object sender, EventArgs e)
        {
            if (await VersionCheck())
            {
                try
                {
                    // 외부 프로그램 실행
                    Process.Start(extractPath + "\\Lost_In_Frost.exe", "/token=" + token);
                }
                catch (Exception ex)
                {
                    // 실행 오류 처리
                    MessageBox.Show("파일 실행 오류: " + ex.Message);
                    // 재설치 권장
                    SetState(NOT);
                    Version();
                }
            }
            else
            {
                SetState(OLD);
            }
            
        }
    }
}

