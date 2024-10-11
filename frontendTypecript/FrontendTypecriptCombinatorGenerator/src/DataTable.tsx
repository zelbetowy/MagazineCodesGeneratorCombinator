import React, { useState } from 'react';

function DataTable({ index }) {
    const [rows, setRows] = useState(Array(5).fill(Array(6).fill(''))); // 5 wierszy, 6 kolumn

    const handleChange = (e, rowIndex, colIndex) => {
        const newRows = rows.map((row, rIdx) =>
            rIdx === rowIndex ? row.map((cell, cIdx) => (cIdx === colIndex ? e.target.value : cell)) : row
        );
        setRows(newRows);
    };

    return (
        <div>
            <h2>Table {index}</h2>
            <table>
                <tbody>
                    {rows.map((row, rowIndex) => (
                        <tr key={rowIndex}>
                            {row.map((cell, colIndex) => (
                                <td key={colIndex}>
                                    <input
                                        type="text"
                                        value={cell}
                                        onChange={(e) => handleChange(e, rowIndex, colIndex)}
                                        placeholder="Wklej tutaj dane"
                                    />
                                </td>
                            ))}
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default DataTable;