import { grade } from "@/type/costume";
import { ReactNode } from "react";
import styled from "styled-components";

interface PipelineProps {
  width: number;
  height: number;
  thickness: number;
  color: string;
}

const Pipeline = ({ width, height, thickness, color }: PipelineProps) => {
  return (
    <PipelineStyle $width={width} $height={height}>
      <Line $thickness={thickness} $color={color} />
    </PipelineStyle>
  );
};

const PipelineStyle = styled.div<{ $width?: number; $height?: number }>`
  width: ${(props) => (props.$width ? `${props.$width}px` : "auto")};
  height: ${(props) => (props.$height ? `${props.$height}px` : "auto")};
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
`;

const Line = styled.div<{ $thickness: number; $color: string }>`
  width: ${(props) => (props.$thickness ? `${props.$thickness}px` : "100%")};
  height: 100%;
  background-color: ${(props) => props.$color};
`;

export default Pipeline;
