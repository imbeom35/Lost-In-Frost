import React, { useRef, useState } from "react";

const useEmailCode = (): [
  Record<string, string>,
  React.MutableRefObject<HTMLInputElement[]>,
  (event: React.KeyboardEvent<HTMLInputElement>) => void,
  (event: React.ChangeEvent<HTMLInputElement>) => void,
  React.Dispatch<React.SetStateAction<Record<string, string>>>
] => {
  const [values, setValues] = useState<Record<string, string>>({
    "1": "",
    "2": "",
    "3": "",
    "4": "",
    "5": "",
    "6": "",
  });

  const inputRefs = useRef<HTMLInputElement[]>([]);

  const updateValues = (propertyName: string, newValue: string) => {
    const updatedValues = { ...values, [propertyName]: newValue };
    setValues(updatedValues);
  };

  const onKeyboardHandler = (event: React.KeyboardEvent<HTMLInputElement>) => {
    const target = event.target as HTMLInputElement;

    if (event.keyCode === 8 && Number(target.name) > 1) {
      if (target.value.length === 0) {
        inputRefs.current[parseInt(target.name, 10) - 1].focus();
        event.preventDefault();
      }
    }
  };

  const onChangeHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
    const target = event.target as HTMLInputElement;

    updateValues(target.name, target.value);

    switch (target.name) {
      case "1":
        if (target.value.length > 0) {
          inputRefs.current[parseInt(target.name, 10) + 1].focus();
        }
        break;
      case "2":
      case "3":
      case "4":
      case "5":
        if (target.value.length > 0) {
          inputRefs.current[parseInt(target.name, 10) + 1].focus();
        } else {
          inputRefs.current[parseInt(target.name, 10) - 1].focus();
        }
        break;
      case "6":
        if (target.value.length === 0) {
          inputRefs.current[parseInt(target.name, 10) - 1].focus();
        }
        break;
    }

    console.log(values);
  };

  return [values, inputRefs, onKeyboardHandler, onChangeHandler, setValues];
};

export default useEmailCode;
