export const calculateBodyMinHeight = (topHeight: number) => {
  return screen.availHeight - (window.outerHeight - window.innerHeight) - topHeight;
};
