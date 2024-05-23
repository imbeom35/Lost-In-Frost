import { getGameFileLauncherDownload } from "@/apis/apiGame";
import CustomButton from "@/component/atoms/button/CustomButton";
import DefaultSpinner from "@/component/atoms/spinner/DefaultSpinner";
import axios, { AxiosResponse } from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { styled } from "styled-components";

interface ModalGameStartProps {
  onClickExit: () => void;
}

const ModalGameStart = ({ onClickExit }: ModalGameStartProps) => {
  const navigate = useNavigate();
  const [mode, setMode] = useState<"START" | "INSTALL">("START");

  useEffect(() => {
<<<<<<< HEAD
    if (!localStorage.getItem("token")) {
=======
    if (!sessionStorage.getItem("token")) {
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
      alert("로그인이 필요합니다.");
      navigate("/user/login");
    } else {
      // 레지스트리 접근
      window.location.replace("LostInFrost://");
    }

    // 런처 통신 요청: Token 전달
    const launcherInterval = setInterval(async () => {
      try {
        await axios
          .post("http://127.0.0.1:200", {
<<<<<<< HEAD
            token: localStorage.getItem("token"),
=======
            token: sessionStorage.getItem("token"),
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
          })
          .then((response) => {
            if (response && response.data === "OK") {
              clearInterval(launcherInterval);
              onClickExit();
            }
          })
          .catch((error) => {
            console.error("API 요청 오류:", error);
          });
      } catch (err) {
        console.log(err);
      }
    }, 1000);

    setTimeout(() => {
      setMode("INSTALL");
    }, 5000);

    return () => {
      clearInterval(launcherInterval);
    };
  }, []);

  const onClickInstall = async () => {
    let filename = "";

    const response = await getGameFileLauncherDownload();
    if (response && response.headers["content-disposition"]) {
      const matchFilename = response.headers["content-disposition"].match(/filename="([^"]+)"/);

      if (matchFilename.length > 1) {
        console.log(matchFilename[1]);
        const splitString: string = matchFilename[1].split("%2F");
        filename = splitString[1];
      }
    }

    const blob = new Blob([response.data], { type: "application/octet-stream" });
    const url = window.URL.createObjectURL(blob);
    const link = document.createElement("a");
    link.href = url;
    link.download = filename;
    link.click();
    link.remove();
    window.URL.revokeObjectURL(url);
  };

  return (
    <ModalShopCostumeStyle>
      <ModalBody>
        <Spinner>
          <DefaultSpinner />
        </Spinner>
        <Title>Lost In Frost</Title>
      </ModalBody>
      <ModalFooter>
        {mode === "START" && <Loading>잠시만 기다려주세요</Loading>}
        {mode === "INSTALL" && (
          <Install>
            런처 설치가 필요합니다
            <CustomButton
              style="default"
              color="black"
              label="다운로드 및 설치"
              size="medium"
              onClick={onClickInstall}
            ></CustomButton>
          </Install>
        )}
      </ModalFooter>
    </ModalShopCostumeStyle>
  );
};

const ModalShopCostumeStyle = styled.div`
  display: flex;
  flex-direction: column;
  padding: 0px 20px 0px 20px;
`;

const ModalBody = styled.div`
  width: 400px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-color: var(--white-default);
`;

const Spinner = styled.div`
  height: 100px;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const Title = styled.div`
  height: 50px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: Pretendard;
  font-size: 24px;
  font-weight: 900;
`;

const ModalFooter = styled.div`
  height: 50px;
  width: 100%;
  display: flex;
  flex-direction: row;
  border-top: 1px solid;
  border-color: var(--gray-default);
  font-family: Pretendard;
  font-size: 16px;
  font-weight: 500;

  > *:nth-child(n + 2) {
    margin-left: 10px;
  }
`;

const Loading = styled.div`
  height: 100%;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const Install = styled.div`
  height: 100%;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

export default ModalGameStart;
