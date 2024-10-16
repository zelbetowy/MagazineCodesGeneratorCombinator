import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import DataGrid from 'react-data-grid';
import './styles/main-page.css';
import axios from 'axios';

const MainPage: React.FC = () => {
    const [numberOfTables, setNumberOfTables] = useState<number>(3); // Domyślnie 3 tabele
    const [tableData, setTableData] = useState(
        Array.from({ length: 3 }, () => [
            { id: 0, A: '', B: '', C: '' },
            { id: 1, A: '', B: '', C: '' },
            { id: 2, A: '', B: '', C: '' }
        ])
    );

    const columns = [
        { key: 'A', name: 'A' },
        { key: 'B', name: 'B' },
        { key: 'C', name: 'C' }
    ];

    // Funkcja do wysłania danych tabel w formacie JSON
    const sendTableData = () => {
        const isDataPresent = tableData.some((table: any) =>
            table.some((row: any) => row.A !== '' || row.B !== '' || row.C !== '')
        );

        if (!isDataPresent) {
            alert('W tabelach nie ma danych do wysłania!');
            return;
        }

        const columnRanges = tableData.reduce((acc, table, index) => {
            acc[`range${index + 1}`] = table;
            return acc;
        }, {});

        axios.post('http://localhost:7070/send_data', columnRanges)
            .then(response => {
                console.log('Dane zostały pomyślnie wysłane:', response.data);
            })
            .catch(error => {
                console.error('Błąd przy wysyłaniu danych:', error);
            });
    };

    const onRowsChange = (rows: any, index: number) => {
        const newData = [...tableData];
        newData[index] = rows;
        setTableData(newData);
    };

    return (
        <div className="main-page">
            <h1>Panel do wklejania danych</h1>
            <label htmlFor="numberOfTables">Wybierz liczbę członów:</label>
            <select
                id="numberOfTables"
                value={numberOfTables}
                onChange={(e) => {
                    const newTableCount = Number(e.target.value);
                    setNumberOfTables(newTableCount);
                    setTableData(Array.from({ length: newTableCount }, () => [
                        { id: 0, A: '', B: '', C: '' },
                        { id: 1, A: '', B: '', C: '' },
                        { id: 2, A: '', B: '', C: '' }
                    ]));
                }}
            >
                <option value={2}>2</option>
                <option value={3}>3</option>
                <option value={4}>4</option>
                <option value={5}>5</option>
                <option value={6}>6</option>
            </select>

            {/* Generowanie tabel */}
            {[...Array(numberOfTables)].map((_, index) => (
                <div key={index} className="data-table">
                    <h2>Table {index + 1}</h2>
                    <DataGrid
                        columns={columns}
                        rows={tableData[index]}
                        onRowsChange={(rows) => onRowsChange(rows, index)}
                        enableCellCopyPaste={true} // Obsługa kopiowania/wklejania wielu komórek
                    />
                </div>
            ))}

            <div className="Button2">
                <button onClick={sendTableData}>Wyślij dane</button>
                <Link to="/">
                    <button>Powrót do strony głównej</button>
                </Link>
            </div>
        </div>
    );
};

export default MainPage;
