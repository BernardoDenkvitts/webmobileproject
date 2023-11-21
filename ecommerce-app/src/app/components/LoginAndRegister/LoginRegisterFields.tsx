import React from 'react';

interface LoginInputProps {
    label: string;
    type: string;
    value: string;
    onChange: (valor: string) => void
}

export default function FormInput(props: LoginInputProps) {
    return (
        <div className="mb-4">
            <label className="block text-white text-sm font-bold mb-2" htmlFor={props.label.toLowerCase()}>
                {props.label}
            </label>
            <input
                type={props.type}
                id={props.label.toLowerCase()}
                className="w-full p-2 border rounded-md text-gray-800"
                placeholder={`${props.label.toLowerCase()}`}
                value={props.value}
                onChange={(e) => props.onChange(e.target.value)}
            />
        </div>
    );
};