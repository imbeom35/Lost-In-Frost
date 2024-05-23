import { styled } from "styled-components";
import image from "@/asset/header_image.png";

interface HeaderImageProps {
  height: number;
}

const HeaderImage = ({ height }: HeaderImageProps) => {
  return <HeaderImageStyle $height={height} $imageUrl={image} />;
};

const HeaderImageStyle = styled.div<{ $height: number; $imageUrl: string }>`
  height: ${(props) => props.$height}px;
  margin-top: 80px;
  background-image: url(${(props) => props.$imageUrl});
  background-size: cover;
  filter: brightness(70%);
  z-index: 1;
`;

export default HeaderImage;
