import styled from "styled-components";
import Navigation from "@/component/organisms/Navigation";
import game from "@/asset/game.gif";

const Home = () => {
  const fullScreen = screen.availHeight - (window.outerHeight - window.innerHeight) - 80;

  return (
    <HomeContainer>
      <Navigation />
      <GifWrapper src={game} $fullScreen={fullScreen}></GifWrapper>
    </HomeContainer>
  );
};

const HomeContainer = styled.div`
  display: flex;
  flex-direction: column;
  margin: 0;
  padding: 0;
`;

const GifWrapper = styled.img<{ $fullScreen?: number }>`
  display: flex;
  ${(props) => (props.$fullScreen ? `height: ${props.$fullScreen}px` : "")};
  margin-top: 80px;
`;

export default Home;
