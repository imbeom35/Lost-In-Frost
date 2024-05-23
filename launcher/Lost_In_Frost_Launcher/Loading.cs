using System;
using System.Net;
using System.Text;
using System.IO;
using System.Windows.Forms;
using Microsoft.Win32;
using Newtonsoft.Json.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace Lost_In_Frost_Launcher
{
    public partial class Loading : Form
    {
        public event EventHandler<string> TokenPassed;

        private String token = null;
        private readonly String extractPath = Environment.GetFolderPath(Environment.SpecialFolder.MyDocuments) + "\\Lost_In_Frost";

        public Loading()
        {
            InitializeComponent();
            this.StartPosition = FormStartPosition.CenterScreen;
            this.FormBorderStyle = FormBorderStyle.FixedSingle;
            this.FormBorderStyle = FormBorderStyle.None;
            Text.Left = (this.ClientSize.Width - Text.Width) / 2;
            Text.Top = (this.ClientSize.Height - Text.Height) / 2;

            // 런처 설치 및 레지스트리 설정
            Setting();

            // 토큰 받아오기
            Task.Run(async () => await GetToken());
        }

        private void Setting()
        {
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

            // 레지스트리 설정
            RegistryKey registryKey = Registry.ClassesRoot.CreateSubKey("LostInFrost");
            registryKey.SetValue("URL Protocol", string.Empty);
            registryKey = registryKey.CreateSubKey("shell").CreateSubKey("open").CreateSubKey("command");
            registryKey.SetValue(string.Empty, extractPath + "\\Lost_In_Frost_Launcher.exe");
        }

        private async Task GetToken()
        {
            Thread timeOutThread = new Thread(TimeOut);
            timeOutThread.Start();

            string apiUrl = "http://127.0.0.1:200/";
            HttpListener listener = new HttpListener();
            listener.Prefixes.Add(apiUrl);
            listener.Start();

            DateTime startTime = DateTime.Now;

            while (true)
            {
                HttpListenerContext context = listener.GetContext();
                HttpListenerRequest request = context.Request;
                HttpListenerResponse response = context.Response;

                // CORS 헤더 추가
                response.Headers.Add("Access-Control-Allow-Origin", "*");
                response.Headers.Add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
                response.Headers.Add("Access-Control-Allow-Headers", "Content-Type, Accept");

                // POST 요청 본문 읽기
                string requestData = "";
                using (StreamReader reader = new StreamReader(request.InputStream))
                {
                    requestData = reader.ReadToEnd();
                }

                // requestData를 받은 경우
                if (requestData?.Length > 0)
                {
                    try
                    {
                        // requestData 파싱
                        JObject json = JObject.Parse(requestData);
                        if (json["token"] != null)
                        {
                            token = json["token"].ToString();
                        }
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show("Launcher Connect Error: " + ex);
                        Application.Exit();
                    }
                }

                // 응답 데이터 반환
                string responseString;
                if (token != null)
                {
                    responseString = "OK";
                }
                else
                {
                    responseString = "TOKEN_NOT_FOUND";
                }
                byte[] buffer = Encoding.UTF8.GetBytes(responseString);
                response.ContentLength64 = buffer.Length;
                Stream output = response.OutputStream;
                output.Write(buffer, 0, buffer.Length);
                output.Close();

                // Token을 받을 경우 통신 종료
                if (token != null)
                {
                    TokenPassed?.Invoke(this, token);
                    break;
                }

                await Task.Delay(100);
            }

            this.Invoke(new Action(() =>
            {
                this.Close();
            }));
        }

        // 종료 조건
        private async void TimeOut()
        {
            DateTime startTime = DateTime.Now;

            while (true)
            {
                if ((DateTime.Now - startTime).TotalSeconds > 3)
                {
                    try
                    {
                        if (!this.IsDisposed)
                        {
                            this.Invoke(new Action(() => this.Close()));
                        }
                    } catch (Exception ex)
                    {
                        Console.WriteLine(ex);
                    }
                    break;
                }

                await Task.Delay(100);
            }
        }
    }
}
